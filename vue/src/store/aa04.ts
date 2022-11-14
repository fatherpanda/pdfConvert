import { defineStore } from 'pinia'

import {getAa04List, Page} from "../api";

export interface aa04Type {
    errdate: String
    docKind: String
    accNo: String
    eventNo: Number
    code: String
    codeSeq: Number
    errno: String
    errmsg: String
    pageno: Number
    pgmid: String
    tableid: String
    addUser: String
    addDate: Date
    addIp: String
    bigo: String
}
export const Aa04Store = defineStore("Aa04Store",
    {
        state: () => ({
            aa02List:[] as aa04Type[],
        }),
        getters: {


        },
        actions: {
            getAa04List(params : Page) {
                return getAa04List(params)
            },

        }
    })