# Rico Portfolio - Designer Portfolio Website

> [中文文档](README-zh.md) | English

A modern, high-performance designer portfolio website template built with Astro. Features a retro blue theme, dark mode support, beautiful animations, and excellent user experience.

![Astro](https://img.shields.io/badge/Astro-5.15.4-FF5D01?logo=astro&logoColor=white)
![Tailwind CSS](https://img.shields.io/badge/Tailwind-4.1.14-38B2AC?logo=tailwind-css&logoColor=white)
![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)

## ✨ Features

- 🚀 **Built with Astro** - Fast and lightweight static site generator
- 🎨 **Modern Design** - Retro blue theme with dark/light mode toggle
- 📱 **Fully Responsive** - Adapts to all device sizes
- 🎭 **Beautiful Animations** - Using AOS and custom animation effects
- 📝 **Blog System** - Supports MDX format blog posts
- 🎯 **Portfolio Showcase** - Elegant portfolio showcase pages
- 🔍 **SEO Optimized** - Built-in SEO and social media tags
- ⚡ **Performance Optimized** - Image optimization, code splitting, lazy loading
- 🌐 **i18n Support** - Easy to extend for multi-language support

## 🛠️ Tech Stack

- **Framework**: [Astro](https://astro.build) 5.15.4 (v6 compatible)
- **Styling**: [Tailwind CSS](https://tailwindcss.com) 4.1.14
- **Animations**: [AOS](https://michalsnik.github.io/aos/)
- **Physics Engine**: [Matter.js](https://brm.io/matter-js/)
- **Content Management**: MDX
- **Type Checking**: TypeScript

## 📦 Installation

### Using Package Manager

```bash

# Using pnpm (recommended)
pnpm install

# Using npm
npm install

# Using yarn
yarn install
```

### Environment Variables Configuration

Copy `.env.example` to `.env` and fill in the corresponding configuration:

```bash
cp .env.example .env
```

Edit the `.env` file and fill in your configuration:

```env
# Site URL (optional, but has default value https://your-domain.com)
# You can skip this on first deployment, but it's recommended to set the correct domain as soon as possible to optimize SEO
PUBLIC_SITE_URL=https://your-domain.com

# Analytics (optional)
PUBLIC_GA4_ID=your-google-analytics-id
PUBLIC_UMAMI_ID=your-umami-id
```

> **Note**: If `PUBLIC_SITE_URL` is not set, it will use the default value `https://your-domain.com`. While it won't cause errors, it's recommended to set the correct domain after deployment to ensure sitemap, RSS feed, and SEO meta tags work properly.

## 🚀 Development

```bash
# Start development server
npm run dev
# or
pnpm dev

# Visit http://localhost:4321
```

## 📦 Build

```bash
# Build for production
npm run build

# Preview build result
npm run preview
```

## 📁 Project Structure

```
├── public/              # Static assets
│   ├── assets/         # Images, videos, etc.
│   └── favicon.png     # Site favicon
├── src/
│   ├── assets/         # Source assets
│   ├── collections/    # Data collections (works, experiences, etc.)
│   ├── components/     # Astro components
│   │   ├── cards/      # Card components
│   │   ├── sections/   # Section components
│   │   ├── ui/         # UI components
│   │   └── widgets/    # Widgets
│   ├── config/         # Configuration files
│   ├── content/        # MDX blog content
│   ├── layouts/        # Layout components
│   ├── pages/          # Page routes
│   ├── scripts/        # Script files
│   └── styles/         # Style files
├── astro.config.mjs    # Astro configuration
├── tailwind.config.mjs # Tailwind configuration
└── package.json        # Project dependencies
```

## 🎨 Customization

### Modify Site Information

Edit the `src/config/site.js` file to modify the site's basic information:

```javascript
export const siteConfig = {
  title: "Your Portfolio",
  author: "Your Name",
  url: "https://your-domain.com",
  // ... more configuration
};
```

### Modify Theme Colors

Edit the CSS variables in the `src/styles/global.css` file:

```css
@theme {
  --color-primary: #2d6dc3;
  --color-primary-dark: #3b7bd9;
  /* ... more color variables */
}
```

### Add Works

Add your work information in `src/collections/works.json`.

### Add Blog Posts

Create new MDX files in the `src/content/post/` directory. The project uses Astro v5 Content Layer API with `glob` loader for content collections, ensuring compatibility with Astro v6.

**Note**: This template has been fully upgraded to Astro v5.15 standards and is compatible with Astro v6:
- ✅ Uses new Content Layer API (`glob` loader)
- ✅ Uses `entry.id` instead of deprecated `entry.slug`
- ✅ Uses `render(entry)` instead of deprecated `entry.render()`
- ✅ Uses `import.meta.env` instead of `process.env`
- ✅ Uses `import.meta.glob()` instead of deprecated `Astro.glob()`
- ✅ All `getStaticPaths()` params are string type (v6 requirement)

## Figma Assets

- **Programming Sticker**: [Figma rogramming-sticker-1-0](https://www.figma.com/community/file/1392100849031958853/programming-sticker-1-0)
- **Bento Cards**：[Figma Bento Cards](https://www.figma.com/community/file/1231184483170475120)
- **Social Cards**: [Figma Bento 2.5d](https://www.figma.com/community/file/1232620929235403629/bento-2-5d-widgets)


## 📧 Contact

- **Author**: Ricoui
- **Blog**: [ricoui.com](https://github.com/ricocc)
- **Email**: hello@ricoui.com
- **Twitter**: [@ricouii](https://x.com/ricouii)
- **GitHub**: [@ricocc](https://github.com/ricocc)


## 💡 Other Products

- **Rico Blog** - Open Source: [https://github.com/ricocc/public-portfolio-site](https://github.com/ricocc/public-portfolio-site)

- **OG Gallery**: [ricoog.com](https://ricoog.com/)


## 🙏 Acknowledgments

- [Astro](https://astro.build) - Excellent static site generator
- [Tailwind CSS](https://tailwindcss.com) - Utility-first CSS framework
- All developers who contributed to this project


## About the Author

I'm Rico, a web/UI designer passionate about creating fun and creative work. I have experience in UI/UX design and am currently focused on web design, visual implementation, and exploring development projects. I regularly update my blog on <a href="https://ricoui.com/" target="_blank">Rico's Blog</a>. You can also follow me on Xiaohongshu  [@Rico的设计漫想](https://www.xiaohongshu.com/user/profile/5f2b6903000000000101f51f) 和 X [@ricouii](https://x.com/ricouii).


Or add me on WeChat—let’s be friends.

<img src="https://ricoui.com/assets/wechat.png" alt="ricocc-wechat" width="280" height="auto" style="display:inline-block;margin:12px;">


## 💜 Support the Author

If you’ve found this helpful, even a small contribution can greatly encourage creators. Thank you!

<img src="https://ricoui.com/assets/zanshangma.jpg" alt="ricocc-wechat" width="280" height="auto" style="display:inline-block;margin:12px;">

<a href="https://ko-fi.com/T6T817U4KZ" target="_blank" style="display:inline-block;margin:.5rem auto 1rem;" data-astro-cid-wlrjxfd7=""><img height="44" style=" border:0px;height:44px;" src="https://storage.ko-fi.com/cdn/kofi2.png?v=6" alt="Buy Me a Coffee at ko-fi.com" data-astro-cid-wlrjxfd7=""></a>

## 📝 Changelog

### Latest Updates (2024)

- **Upgraded to Astro 5.15.4** - Fully compliant with Astro v5.15 standards and compatible with Astro v6
- **Content Collections Upgrade** - Using new Content Layer API, all legacy APIs removed
- **API Modernization** - All deprecated APIs updated to latest standards
- **Performance Optimization** - Optimized build and runtime performance

⭐ If this project helps you, please give it a Star!
