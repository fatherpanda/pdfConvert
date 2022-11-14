import {createApi, useApi} from './useApi'
import {Aa02Pk} from "../store/aa02";
import {ba02Pk} from "../store/ba02";
import {aa01Type} from "../store/aa01";
const api = useApi()

export interface upload {
    file: Object
}
export interface Page {
    page?: Number
    size?: Number
    sort?: String
}
export interface aa01DelType {
    gubn: String[]
}
async function upload(params: upload) {
    const url = '/upload'
    const formData = setFormData(params)
    const response = await api.post(url, formData, {
        headers: {
            'Content-Type': 'multipart/form-data',
        },
    })
    return Promise.resolve(response.data)
}
async function getAa01List (params: Page) {
    const url = '/api/aa01'
    // @ts-ignore
    const response = await api.get(url, {params:params})
    return Promise.resolve(response.data)
}

async function getAa02List (params: Page) {
    const url = '/api/aa02'
    // @ts-ignore
    const response = await api.get(url, params)
    return Promise.resolve(response.data)
}
async function getAa04List (params: Page) {
    const url = '/api/aa04'
    // @ts-ignore
    const response = await api.get(url, params)
    return Promise.resolve(response.data)
}
async function getBa02List (params: Page) {
    const url = '/api/ba02'
    // @ts-ignore
    const response = await api.get(url, params)
    return Promise.resolve(response.data)
}


async function getBa04List (params: Page) {
    const url = '/api/ba04'
    // @ts-ignore
    const response = await api.get(url, params)
    return Promise.resolve(response.data)
}

async function deleteAa01 (params: aa01DelType) {
    const url = '/api/aa01'
    // @ts-ignore
    const response = await api.delete(url, {data:params})
    return Promise.resolve(response.data)
}
async function deleteAa02 (params: Aa02Pk[]) {
    const url = '/api/aa02'
    // @ts-ignore
    const response = await api.delete(url, {data:params})
    return Promise.resolve(response.data)
}

async function deleteBa02 (params: ba02Pk[]) {
    const url = '/api/ba02'
    // @ts-ignore
    const response = await api.delete(url, {data:params})
    return Promise.resolve(response.data)
}

async function deleteBa04 (params: ba02Pk[]) {
    const url = '/api/ba04'
    // @ts-ignore
    const response = await api.delete(url, {data:params})
    return Promise.resolve(response.data)
}

async function writeAa01 (params: aa01Type) {
    const url = '/api/aa01'
    // @ts-ignore
    const response = await api.post(url, params)
    return Promise.resolve(response.data)
}

function setFormData(item: any): FormData {
    const form_data = new FormData()

    for (const key in item) {
        form_data.append(key, item[key])
    }
    return form_data
}
export {
    upload,
    getAa01List,
    getAa02List,
    getAa04List,
    getBa02List,
    getBa04List,
    deleteAa01,
    deleteAa02,
    deleteBa02,
    deleteBa04,
    writeAa01,
}