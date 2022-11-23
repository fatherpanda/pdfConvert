import { defineStore } from 'pinia'

import {deleteAa02, getAa02List, writeAa02, getAa02SelectCode,Page} from "../api";

export interface aa02Type {
    aa02PK: Aa02Pk
    useyn: String
    sortOrder: String
    attr: String
    cname: String
    attr1: String
    code1: String
    desc1: String
    attr2: String
    code2: String
    desc2: String
    attr3: String
    code3: String
    desc3: String
    attr4: String
    code4: String
    desc4: String
    attr5: String
    code5: String
    desc5: String
    addUser: String
    addDate: Date
    addIp: String
    updateUser: String
    updateDate: Date
    updateIp: String
    bigo: String
}
export interface Aa02Pk {
    gubn: String
    code: String

}
export const Aa02Store = defineStore("Aa02Store",
    {
        state: () => ({
            aa02List:[] as aa02Type[],
            code:{
                BA0103:[] as aa02Type[],
                BA0105:[] as aa02Type[]
            },
        }),
        getters: {


        },
        actions: {
            getAa02List(params : Page) {
                return getAa02List(params)
            },
            delete(params : Aa02Pk[]) {
                return deleteAa02(params)
            },
            write(params: aa02Type) {
                return writeAa02(params)
            },
            getCode(gubn:String) {
                const result=getAa02SelectCode(gubn)
                result.then((response)=>{
                    // @ts-ignore
                    this.code[gubn]=response
                }).catch((error)=>{
                    console.log(error)
                })
            }

        }
    })