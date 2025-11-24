# 李陈康的个人网站

基于 Astro + Tailwind 的中文个人站，记录我在景德镇艺术职业大学计算机专业的学习、实习与校园项目。所有页面保留原模板的动效与视觉，但内容已经全部换成 Java 后端/全栈方向的真实经历。

## 功能模块
- **首页**：中文自我介绍、社交名片、精选作品、最新博客。
- **作品集**：校园脉搏协作平台、向阳社团模板、Nova 3D 素材等案例。
- **博客**：多租户架构、SSE 实时通信、CI/CD、竞赛系统等实践复盘。
- **关于我**：教育背景、技能栈、实习与荣誉，附邮箱 `hello@lichenkang.dev`。

## 快速开始
> 需要 Node.js 18+ 与 pnpm。

```bash
pnpm install
pnpm dev      # http://localhost:5200
```

构建与预览：
```bash
pnpm build
pnpm preview   # 可选，本地预览 dist
```

## 数据与内容
- `src/collections/*.json`：导航、作品、社交名片、经历等数据源，全部中文可直接编辑。
- `src/content/post`：MDX 文章目录，包含实习复盘与校园项目日志。
- `public/assets`：静态资源（头像、作品截图、3D 素材等）。

## 部署
项目默认输出静态文件到 `dist/`，可部署到 Vercel、Netlify 或任意静态托管平台。线上部署前记得在 `.env` 或 Vercel 环境变量中设置 `PUBLIC_SITE_URL`。

欢迎将本项目作为模板，也欢迎通过 issue / 邮件交流你的想法。***
