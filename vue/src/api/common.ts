import {ref} from "vue";

interface dataGridType {
    headerName:String
    field:String
    isList:boolean
    isWrite:boolean
    listOption?:listOptionType
    writeOption:writeOptionType
}
interface listOptionType {
    headerName?:String
    field?:String
    checkboxSelection:boolean
}
interface writeOptionType {
    headerName?:String
    field?:String
    type:String
}
const extractField=(defaultData : dataGridType[])=>{
    const fieldConfig={
        list:[] as listOptionType[],
        write:[] as writeOptionType[],
        dataDefault:{} as any
    }
    defaultData.forEach((row)=>{
        if(row.isList) {
            const line=Object.assign({headerName:row.headerName,field:row.field}, row.listOption) as listOptionType
            fieldConfig.list.push(line)
        }
        if(row.isWrite) {
            const write=Object.assign({headerName:row.headerName,field:row.field}, row.writeOption)
            let dataDefault=fieldConfig.dataDefault
            let field=row.field
            if(field.indexOf(".")>-1) {

                let key=field.substring(0, field.indexOf("."))
                field=field.substring(field.indexOf(".")+1,field.length)
                if(fieldConfig.dataDefault[key]==undefined) {
                    fieldConfig.dataDefault[key] = {}
                }
                dataDefault=fieldConfig.dataDefault[key]
            }
            fieldConfig.write.push(write)
            if(row.writeOption.type==='text' || row.writeOption.type==='radio') {
                // @ts-ignore
                dataDefault[field]=''
            } else if(row.writeOption.type==='number') {
                // @ts-ignore
                dataDefault[field]=0
            }
        }
    })
}