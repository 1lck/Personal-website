## 李陈康的个人网站

项目基于 Astro + Tailwind + MDX，记录我在景德镇艺术职业大学的学习、实习与校园项目实践。内容和数据均换成中文版本，保留原模板的动画与排版。

### 主要模块
- **首页**：中文个人简介、社交名片、精选作品、最新文章。
- **作品**：校园脉搏协作平台、向阳社团模板、Nova 3D 资源包等。
- **博客**：多租户架构、VR 全景优化、AI 客服 SSE 等实习复盘。
- **关于我**：教育背景、技能栈、实习经历、荣誉奖项。

### 本地开发
```bash
pnpm install
pnpm dev
```
默认访问 `http://localhost:5200`。

### 构建
```bash
pnpm build
```
静态文件位于 `dist/`，可部署到 Vercel、Netlify 等平台。

### 数据来源
- `src/collections`：导航、作品、社交、经历等 JSON 数据。
- `src/content/post`：全部中文 MDX 文章，可直接编辑。
- `public/assets`：静态图片与多媒体资源。

如需交流或获取模板，欢迎联系我：`hello@lichenkang.dev`。 
