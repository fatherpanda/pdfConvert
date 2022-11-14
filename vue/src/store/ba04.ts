import { defineStore } from 'pinia'

import {deleteBa04, getBa04List, Page} from "../api";
import {ba02Pk} from "./ba02";

export interface ba04Type {
    ba04PK:ba02Pk
    sortOrder: Number
    subOrder: Number
    attr: String
    codeDesc: String
    category: String
    orgCode: String
    addUser: String
    addDate: Date
    addIp: String
    updateUser: String
    updateDate: Date
    updateIp: String
    bigo: String
}
export const Ba04Store = defineStore("Ba04Store",
    {
        state: () => ({
            ba04List:[] as ba04Type[],
        }),
        getters: {


        },
        actions: {
            getBa04List(params : Page) {
                return getBa04List(params)
            },
            delete(params : Ba02Pk) {
                return deleteBa04(params)
            },


        }
    })