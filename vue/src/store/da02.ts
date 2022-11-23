import { defineStore } from 'pinia'

import {getDa02List, getDa02ListAll, Page, writeDa02, getDa02EventNo} from "../api";

export interface da02Type {
    da02PK:da02PkType
    sortOrder: Number
    subOrder: Number
    attr: String
    recodeTime: String
    recodeValue: String
    addUser: String
    addDate: Date
    addIp: String
    updateUser: String
    updateDate: Date
    updateIp: String
    bigo: String

}
export interface da02PkType {
    accNo: String
    eventNo: Number
    dssadCode?: String

}
export const Da02Store = defineStore("Da02Store",
    {
        state: () => ({
            da02List:[] as da02Type[],
            eventNo:[] as Number[]
        }),
        getters: {


        },
        actions: {
            getDa02List(params : Page) {
                return getDa02List(params)
            },
            getDa02ListAll(params : da02PkType) {
                return getDa02ListAll(params)
            },
            delete(params : String) {
                return null
            },
            write(params:da02Type) {
                return writeDa02(params)
            },
            getDa02EventNo() {
                const result=getDa02EventNo()
                result.then((response) => {
                    this.eventNo=response
                }).catch((error) => {
                    console.log(error)
                })
            },

        }
    })