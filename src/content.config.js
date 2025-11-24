// 1. 从 `astro:content` 导入工具函数
import { defineCollection, z } from 'astro:content';

// 2. 导入加载器
import { glob } from 'astro/loaders';

// 3. 定义你的集合
const post = defineCollection({
	// 使用 glob 加载器从 src/content/post 目录加载所有 .mdx 文件
	loader: glob({ pattern: '**/*.{md,mdx}', base: './src/content/post' }),
	// Type-check frontmatter using a schema
	schema: z.object({
		title: z.string(),
		description: z.string(),
		publishDate: z.coerce.date(),
		read: z.number().optional(),
		tags: z.array(z.string()).optional(),
		img: z.string().optional(),
		img_alt: z.string().optional(),
		featured: z.boolean().optional(),
	}),
});

// 4. 导出一个 `collections` 对象来注册你的集合
export const collections = { post };

