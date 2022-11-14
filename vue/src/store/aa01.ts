import { defineStore } from 'pinia'

import {deleteAa01, getAa01List, writeAa01, Page} from "../api";

export interface aa01Type {
    gubun: String
    useyn: String
    sortOrder: Number
    attr: String
    cname: String
    convType: String
    memo: String
    addUser?: String
    addDate?: Date
    addIp?: String
    updateUser?: String
    updateDate?: Date
    updateIp?: String
    bigo: String
}
export const Aa01Store = defineStore("Aa01Store",
    {
        state: () => ({
            aa01List:[] as aa01Type[],
        }),
        getters: {


        },
        actions: {
            getAa01List(params : Page) {
                return getAa01List(params)
            },
            delete(gubn : String[]) {
                return deleteAa01({gubn:gubn})
            },
            write(params: aa01Type)
            {
                return writeAa01(params)
            }

        }
    })