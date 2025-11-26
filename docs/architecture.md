# 系统整体设计

## 总体思路
- 将前端 Astro 站点改造为“展示层 + 轻量管理端”，所有可变内容都通过 REST API 与 Spring Boot 后端通信。
- Spring Boot 提供统一的业务层，负责**文本信息落库**与**媒体文件磁盘存储**，不依赖任何云 OSS。
- 通过 `.env`(前端) 与 `application.yml`(后端) 暴露可配置项，避免硬编码，方便未来迁移至云服务器。

## 后端（Spring Boot 3）
- 采用 `spring-boot-starter-web`、`spring-data-jpa`、`spring-boot-starter-validation`，数据库使用 SQLite 文件（`storage/data/portfolio.db`），兼顾轻量与可移植性。
- 核心实体
  - `Project`：包含标题、描述、链接、封面/视频路径、标签列表、排序位、是否精选。
  - `BlogPost`：包含 slug、摘要、Markdown 正文、封面、发布时间、是否置顶、发布状态。
  - `Profile`：保存个人简介、联系方式、社交链接（Map 存储）以及 Hero/简历等资源路径。
  - `MediaAsset`：统一记录上传文件的原始名、相对路径、MIME、大小，便于后台管理。
- 服务层
  - `FileStorageService`：根据 `content.storage-root` 配置将文件写入磁盘，生成唯一文件名并返回访问 URL。
  - `ProjectService` / `BlogPostService` / `ProfileService`：封装 CRUD、DTO 映射与业务校验。
- 控制器
  - `/api/public/**`：提供前端展示调用（只读）。
  - `/api/admin/**`：后台管理 CRUD（采用基础的 token header 校验，默认读取 `content.admin-token`）。
  - `/api/files/**`：直接回源磁盘文件，满足“内容不经 OSS”要求。
- 其他
  - 通过 `@ConfigurationProperties` 暴露存储路径、媒体 URL、CORS 白名单等参数。
  - 初始数据脚本（`data.sql`）仅注入示例内容，可安全删除或扩展。

## 前端（Astro）
- `.env` 新增 `PUBLIC_API_BASE_URL`，所有前端请求都读取该变量，默认指向 `http://localhost:8080`.
- 抽象 `src/lib/runtime-config.ts` 和 `src/scripts/api-client.ts`，统一封装 `fetch`、错误处理与 Loading 状态，前端不直接写死 URL。
- 展示层（首页 Hero、精选项目、博客列表等）改为客户端脚本渲染，实时从 `/api/public/*` 拉取数据，无需重新构建站点即可更新。
- 新增 `/admin` 页面集合：以表单 + 列表形式调用 `/api/admin/*`，上传文件直接 POST 到后端，满足自用后台的需求（未做复杂权限，仅需自定义 token）。

## 存储策略
- **数据库**：SQLite 文件放置于 `storage/data`（可通过 `CONTENT_DATA_DIR` 环境变量覆盖），文本信息全部入库。
- **媒体文件**：磁盘路径 `storage/media/{projects|posts|profile}`；返回给前端的 URL 形如 `${CONTENT_MEDIA_BASE_URL}/{relativePath}`。
- **备份**：`storage/` 整体可通过挂载卷或对象存储快照备份，部署时保证同进程即可。

## 部署建议
1. 后端通过 `./mvnw spring-boot:run` 或打包 Jar 运行，暴露 8080 端口。
2. 前端 `npm run build && npm run preview`（或使用 `adapter-node` 部署），配置 `PUBLIC_API_BASE_URL` 指向后端入口。
3. 生产环境建议通过 Nginx 反向代理：`/` 转发至 Astro，`/api` & `/files` 转发至 Spring Boot。
4. 若未来迁移至云服务器，只需同步 `storage/` 目录与 `.env`/`application.yml`，无需重新初始化数据。

