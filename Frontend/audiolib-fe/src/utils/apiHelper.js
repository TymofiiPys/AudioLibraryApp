import { API_BASE_URL } from "../api";  // Import the base URL

export const apiRequest = async (endpoint, method, body = null) => {
  const options = {
    method,
    headers: { "Content-Type": "application/json" },
  };

  if (body) {
    options.body = JSON.stringify(body);
  }

  // The proxy will forward requests from /api to http://localhost:8080
  const response = await fetch(API_BASE_URL + endpoint, options); // This is now /api/login
  if (!response.ok) {
    throw new Error(await response.text() || "Request failed");
  }

  return await response.json();
};