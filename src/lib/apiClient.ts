import { runtimeConfig } from "@/lib/runtime-config";

export type Project = {
  id: number;
  title: string;
  description?: string;
  link: string;
  coverImageUrl?: string;
  videoUrl?: string;
  tags?: string[];
  featured?: boolean;
  published?: boolean;
  sortOrder?: number;
};

export type BlogPost = {
  id: number;
  title: string;
  slug: string;
  summary?: string;
  content?: string;
  contentHtml?: string;
  headings?: {
    slug: string;
    text: string;
    depth: number;
  }[];
  coverImageUrl?: string;
  publishDate: string;
  featured?: boolean;
  tags?: string[];
};

export type Profile = {
  id: number;
  fullName: string;
  headline?: string;
  bio?: string;
  email?: string;
  phone?: string;
  wechat?: string;
  location?: string;
  jobTitle?: string;
  heroImageUrl?: string;
  resumeUrl?: string;
  primaryCtaLabel?: string;
  primaryCtaLink?: string;
  secondaryCtaLabel?: string;
  secondaryCtaLink?: string;
  socialLinks?: Record<string, string>;
};

export type Work = {
  id: number;
  name: string;
  description?: string;
  url: string;
  imageUrl?: string;
  videoUrl?: string;
  tags?: string[];
  isShow?: boolean;
  sortOrder?: number;
};

async function request<T>(path: string, init?: RequestInit): Promise<T> {
  const response = await fetch(`${runtimeConfig.publicApiBaseUrl}${path}`, {
    cache: "no-store",
    ...init,
    headers: {
      "Content-Type": "application/json",
      ...(init?.headers || {}),
    },
  });

  if (!response.ok) {
    const message = await response.text();
    throw new Error(message || `Request failed: ${response.status}`);
  }

  return (await response.json()) as T;
}

export const apiClient = {
  fetchProjects() {
    return request<Project[]>("/projects");
  },
  fetchBlogPosts() {
    return request<BlogPost[]>("/blog-posts");
  },
  fetchBlogPost(slug: string) {
    return request<BlogPost>(`/blog-posts/${slug}`);
  },
  fetchProfile() {
    return request<Profile | null>("/profile");
  },
  fetchWorks() {
    return request<Work[]>("/works");
  },
};
