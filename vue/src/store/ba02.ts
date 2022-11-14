import { defineStore } from 'pinia'

import {deleteBa02, getBa02List, Page} from "../api";

export interface ba02Type {
    ba02PK:ba02Pk
    reqfield: String
    absMethod: String
    title: String
    subTitle: String
    descName: String
    firstLine: String
    recordTime: String
    recordValue: String
    worksname: String
    convType: String
    carCompany: String
    carKind: String
    addUser: String
    addDate: Date
    addIp: String
    updateUser: String
    updateDate: Date
    updateIp: String
    bigo: String
}
export interface ba02Pk {
    toolVersion: String
    reportVersion: String
    code: String
}
export const Ba02Store = defineStore("Ba02Store",
    {
        state: () => ({
            ba02List:[] as ba02Type[],
        }),
        getters: {


        },
        actions: {
            getBa02List(params : Page) {
                return getBa02List(params)
            },
            delete(params : ba02Pk[]) {
                return deleteBa02(params)
            },

        }
    })