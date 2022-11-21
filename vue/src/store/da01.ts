import { defineStore } from 'pinia'

import {deleteBa04, getDa01List, Page, writeDa01} from "../api";
import da01Write from "../views/Da01Write.vue";

export interface da01Type {
    accNo: String
    prodName: String
    carDesc: String
    vin: String
    userName: String
    writeDate: String
    toolVersion: String
    reportVersion: String
    statusFlag: String
    docPath: String
    addUser: String
    addDate?: Date
    addIp?: String
    updateUser?: String
    updateDate?: Date
    updateIp?: String
    bigo: String,
    pdfFile?:File

}
export const Da01Store = defineStore("Da01Store",
    {
        state: () => ({
            da01List:[] as da01Type[],
        }),
        getters: {


        },
        actions: {
            getDa01List(params : Page) {
                return getDa01List(params)
            },
            delete(params : String) {
                return null
            },
            write(params:da01Type) {
                return writeDa01(params)
            },
            getBean() {
                const da01 : da01Type={
                    accNo: '',
                    prodName: '',
                    carDesc: '',
                    vin: '',
                    userName: '',
                    writeDate: '',
                    toolVersion: '',
                    reportVersion: '',
                    statusFlag: '',
                    docPath:'',
                    addUser: '',
                    bigo: '',
                }
                return da01
            }

        }
    })