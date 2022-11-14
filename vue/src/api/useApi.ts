import axios, { AxiosInstance } from 'axios'


let api: AxiosInstance

export function createApi() {
  // Here we set the base URL for all requests made to the api
  api = axios.create({
    baseURL : 'http://localhost:8080'
  })
  return api
}

export function useApi() {
  if (!api) {
    createApi()
  }
  return api
}
