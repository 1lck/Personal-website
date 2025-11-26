const apiBase = (import.meta.env.PUBLIC_API_BASE_URL || 'http://localhost:8080').replace(/\/$/, '');
const mediaBase = (import.meta.env.PUBLIC_MEDIA_BASE_URL || `${apiBase}/api/files`).replace(/\/$/, '');

export const runtimeConfig = {
  apiBaseUrl: apiBase,
  publicApiBaseUrl: `${apiBase}/api/public`,
  adminApiBaseUrl: `${apiBase}/api/admin`,
  mediaBaseUrl: mediaBase,
};