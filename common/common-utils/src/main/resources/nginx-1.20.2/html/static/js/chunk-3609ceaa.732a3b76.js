(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-3609ceaa"],{"2c54":function(t,e,a){"use strict";a("6d32")},"47a7":function(t,e,a){"use strict";a.r(e);var o=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"app-container"},[a("el-form",{attrs:{"label-width":"120px",size:"small"}},[a("div",{staticStyle:{"background-color":"#E0E0E0",width:"100%",padding:"0 10px",margin:"10px 0"}},[a("h3",[t._v("基本信息")])]),a("el-form-item",{attrs:{label:"sku名称"}},[a("el-input",{model:{value:t.skuInfo.skuName,callback:function(e){t.$set(t.skuInfo,"skuName",e)},expression:"skuInfo.skuName"}})],1),a("el-form-item",{attrs:{label:"商品分类",prop:"tmId"}},[a("el-select",{attrs:{placeholder:"请选择"},model:{value:t.skuInfo.categoryId,callback:function(e){t.$set(t.skuInfo,"categoryId",e)},expression:"skuInfo.categoryId"}},t._l(t.categoryList,(function(t){return a("el-option",{key:t.id,attrs:{label:t.name,value:t.id}})})),1)],1),a("el-form-item",{attrs:{label:"SKU编号："}},[a("el-input",{model:{value:t.skuInfo.skuCode,callback:function(e){t.$set(t.skuInfo,"skuCode",e)},expression:"skuInfo.skuCode"}})],1),a("el-form-item",{attrs:{label:"商品售价："}},[a("el-input",{model:{value:t.skuInfo.price,callback:function(e){t.$set(t.skuInfo,"price",e)},expression:"skuInfo.price"}})],1),a("el-form-item",{attrs:{label:"市场价："}},[a("el-input",{model:{value:t.skuInfo.marketPrice,callback:function(e){t.$set(t.skuInfo,"marketPrice",e)},expression:"skuInfo.marketPrice"}})],1),a("el-form-item",{attrs:{label:"是否新人专享:"}},[a("el-radio-group",{model:{value:t.skuInfo.isNewPerson,callback:function(e){t.$set(t.skuInfo,"isNewPerson",e)},expression:"skuInfo.isNewPerson"}},[a("el-radio",{attrs:{label:0}},[t._v("否")]),a("el-radio",{attrs:{label:1}},[t._v("是")])],1)],1),a("el-form-item",{attrs:{label:"排序"}},[a("el-input",{model:{value:t.skuInfo.sort,callback:function(e){t.$set(t.skuInfo,"sort",e)},expression:"skuInfo.sort"}})],1),a("div",{staticStyle:{"background-color":"#E0E0E0",width:"100%",padding:"0 10px",margin:"10px 0"}},[a("h3",[t._v("平台属性")])]),a("el-form-item",{attrs:{label:"属性分组",prop:"tmId"}},[a("el-select",{attrs:{placeholder:"请选择"},on:{change:t.selectAttrChange},model:{value:t.skuInfo.attrGroupId,callback:function(e){t.$set(t.skuInfo,"attrGroupId",e)},expression:"skuInfo.attrGroupId"}},t._l(t.attrGroupList,(function(t){return a("el-option",{key:t.id,attrs:{label:t.name,value:t.id}})})),1)],1),a("el-form-item",{attrs:{label:"平台属性："}},[a("el-card",{staticClass:"cardBg",attrs:{shadow:"never"}},t._l(t.attrList,(function(e,o){return a("div",{key:e.id,class:{littleMarginTop:0!==o}},[a("div",{staticClass:"paramInputLabel"},[t._v(t._s(e.name)+":")]),1===e.inputType?a("el-select",{staticClass:"paramInput",staticStyle:{width:"300px"},model:{value:t.attrList[o].value,callback:function(e){t.$set(t.attrList[o],"value",e)},expression:"attrList[index].value"}},t._l(t.getParamSelectList(e.selectList),(function(t){return a("el-option",{key:t,attrs:{label:t,value:t}})})),1):a("el-input",{staticClass:"paramInput",staticStyle:{width:"300px"},model:{value:t.attrList[o].value,callback:function(e){t.$set(t.attrList[o],"value",e)},expression:"attrList[index].value"}})],1)})),0)],1),a("div",{staticStyle:{"background-color":"#E0E0E0",width:"100%",padding:"0 10px",margin:"10px 0"}},[a("h3",[t._v("商品图片")])]),a("el-form-item",{attrs:{label:"图片上传"}},[a("el-upload",{staticClass:"upload-demo",attrs:{"on-success":t.onUploadSuccess,"before-upload":t.beforeUpload,"on-preview":t.onUploadPreview,"on-remove":t.onUploadRemove,multiple:!0,action:t.BASE_API+"/admin/product/fileUpload","list-type":"picture-card","file-list":t.fileList}},[a("i",{staticClass:"el-icon-plus"}),a("div",{staticClass:"el-upload__tip",attrs:{slot:"tip"},slot:"tip"},[t._v("只能上传jpg/png/gif文件，且不超过2MB")])])],1),a("div",{staticStyle:{"background-color":"#E0E0E0",width:"100%",padding:"0 10px",margin:"10px 0"}},[a("h3",[t._v("商品库存")])]),a("el-form-item",{attrs:{label:"商品库存："}},[a("el-input",{model:{value:t.skuInfo.stock,callback:function(e){t.$set(t.skuInfo,"stock",e)},expression:"skuInfo.stock"}})],1),a("el-form-item",{attrs:{label:"商品预警库存："}},[a("el-input",{model:{value:t.skuInfo.lowStock,callback:function(e){t.$set(t.skuInfo,"lowStock",e)},expression:"skuInfo.lowStock"}})],1),a("el-form-item",{attrs:{label:"限购个数："}},[a("el-input",{model:{value:t.skuInfo.perLimit,callback:function(e){t.$set(t.skuInfo,"perLimit",e)},expression:"skuInfo.perLimit"}})],1),a("div",{staticStyle:{"background-color":"#E0E0E0",width:"100%",padding:"0 10px",margin:"10px 0"}},[a("h3",[t._v("商品海报")])]),a("el-form-item",{attrs:{label:"上传海报"}},[a("el-upload",{staticClass:"upload-demo",attrs:{"on-success":t.onUploadPosterSuccess,"before-upload":t.beforeUpload,"on-preview":t.onUploadPreview,"on-remove":t.onUploadPosterRemove,multiple:!0,action:t.BASE_API+"/admin/product/fileUpload","list-type":"picture-card","file-list":t.filePosterList}},[a("i",{staticClass:"el-icon-plus"}),a("div",{staticClass:"el-upload__tip",attrs:{slot:"tip"},slot:"tip"},[t._v("只能上传jpg/png/gif文件，且不超过2MB")])])],1),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:t.saveOrUpdate}},[t._v("保存")]),a("el-button",{on:{click:t.back}},[t._v("返回")])],1)],1),a("el-dialog",{attrs:{visible:t.dialogImageVisible},on:{"update:visible":function(e){t.dialogImageVisible=e}}},[a("img",{attrs:{src:t.dialogImageUrl,width:"100%",alt:""}})])],1)},s=[],n=a("5530"),i=(a("d3b7"),a("159b"),a("b0c0"),a("b7c5")),r=a("aab4"),c=a("5a3f"),u=a("d534"),l={id:"",categoryId:"",attrGroupId:"",skuType:0,skuName:"",perLimit:1,isNewPerson:0,sort:"",imgUrl:"",skuCode:"",price:"",marketPrice:"",stock:"",lowStock:"",skuAttrValueList:[],skuPosterList:[],skuImagesList:[]},d={data:function(){return{BASE_API:"http://localhost:8203",skuInfo:l,saveBtnDisabled:!1,categoryList:[],attrGroupList:[],attrList:[],fileList:[],filePosterList:[],dialogImageUrl:"",dialogImageVisible:!1}},watch:{$route:function(t,e){console.log("路由变化......"),console.log(t),console.log(e),this.init()}},created:function(){console.log("form created ......"),this.init()},methods:{init:function(){if(this.$route.params&&this.$route.params.id){var t=this.$route.params.id;this.fetchDataById(t)}else this.skuInfo=Object(n["a"])({},l);this.fetchCategoryList(),this.fetchAttrGroupList()},fetchCategoryList:function(){var t=this;r["a"].findAllList().then((function(e){t.categoryList=e.data}))},fetchAttrGroupList:function(){var t=this;c["a"].findAllList().then((function(e){t.attrGroupList=e.data}))},fetchAttrList:function(t){var e=this;u["a"].getList(t).then((function(t){e.attrList=t.data,e.skuInfo.skuAttrValueList.length>0&&e.attrList.forEach((function(t){e.skuInfo.skuAttrValueList.forEach((function(e){t.id===e.attrId&&(t.value=e.attrValue)}))}))}))},getParamSelectList:function(t){return t.split(",")},selectAttrChange:function(t){this.fetchAttrList(t)},saveOrUpdate:function(){this.skuInfo.id?this.updateData():this.saveData()},saveData:function(){var t=this,e=[];this.attrList.forEach((function(t){e.push({attrId:t.id,attrName:t.name,attrValue:t.value})})),this.skuInfo.skuAttrValueList=e,this.skuInfo.skuImagesList.length>0&&(this.skuInfo.imgUrl=this.skuInfo.skuImagesList[0].imgUrl),i["a"].save(this.skuInfo).then((function(e){e.code&&(t.$message({type:"success",message:e.message}),t.$router.push({path:"/product/skuInfo/list"}),t.saveBtnDisabled=!1)}))},updateData:function(){var t=this,e=[];this.attrList.forEach((function(t){e.push({attrId:t.id,attrName:t.name,attrValue:t.value})})),this.skuInfo.skuAttrValueList=e,this.skuInfo.skuImagesList.length>0&&(this.skuInfo.imgUrl=this.skuInfo.skuImagesList[0].imgUrl),i["a"].updateById(this.skuInfo).then((function(e){e.code&&(t.$message({type:"success",message:e.message}),t.$router.push({path:"/product/skuInfo/list"}))}))},back:function(){this.$router.push({path:"/product/skuInfo/list"})},fetchDataById:function(t){var e=this;i["a"].getById(t).then((function(t){e.skuInfo=t.data,e.fetchAttrList(e.skuInfo.attrGroupId),e.skuInfo.skuImagesList.forEach((function(t){var a=new Object;a.url=t.imgUrl,e.fileList.push(a)})),e.skuInfo.skuPosterList.forEach((function(t){var a=new Object;a.url=t.imgUrl,e.filePosterList.push(a)}))}))},beforeUpload:function(t){var e="image/jpeg"===t.type,a="image/png"===t.type,o="image/gif"===t.type,s=t.size/1024/1024<2;return e||a||o?!!s||(this.$message.error("上传头像图片大小不能超过 2MB!"),!1):(this.$message.error("上传头像图片只能是 JPG、PNG 或 GIF 格式!"),!1)},onUploadSuccess:function(t,e){this.skuInfo.skuImagesList.push({imgName:e.name,imgUrl:e.response.data})},onUploadPreview:function(t){this.dialogImageUrl=t.url,this.dialogImageVisible=!0},onUploadRemove:function(t,e){var a=[];this.skuInfo.skuImagesList.forEach((function(e){t.url!=e.imgUrl&&a.push(e)})),this.skuInfo.skuImagesList=a},onUploadPosterSuccess:function(t,e){this.skuInfo.skuPosterList.push({imgName:e.name,imgUrl:e.response.data})},onUploadPosterRemove:function(t,e){var a=[];this.skuInfo.skuPosterList.forEach((function(e){t.url!=e.imgUrl&&a.push(e)})),this.skuInfo.skuPosterList=a}}},f=d,p=(a("2c54"),a("2877")),m=Object(p["a"])(f,o,s,!1,null,"23f9fbfc",null);e["default"]=m.exports},"5a3f":function(t,e,a){"use strict";a("99af");var o=a("b775"),s="/admin/product/attrGroup";e["a"]={getPageList:function(t,e,a){return Object(o["a"])({url:"".concat(s,"/").concat(t,"/").concat(e),method:"get",params:a})},getById:function(t){return Object(o["a"])({url:"".concat(s,"/get/").concat(t),method:"get"})},save:function(t){return Object(o["a"])({url:"".concat(s,"/save"),method:"post",data:t})},updateById:function(t){return Object(o["a"])({url:"".concat(s,"/update"),method:"put",data:t})},removeById:function(t){return Object(o["a"])({url:"".concat(s,"/remove/").concat(t),method:"delete"})},removeRows:function(t){return Object(o["a"])({url:"".concat(s,"/batchRemove"),method:"delete",data:t})},findAllList:function(){return Object(o["a"])({url:"".concat(s,"/findAllList"),method:"get"})}}},"6d32":function(t,e,a){},aab4:function(t,e,a){"use strict";a("99af");var o=a("b775"),s="/admin/product/category";e["a"]={getPageList:function(t,e,a){return Object(o["a"])({url:"".concat(s,"/").concat(t,"/").concat(e),method:"get",params:a})},getById:function(t){return Object(o["a"])({url:"".concat(s,"/get/").concat(t),method:"get"})},save:function(t){return Object(o["a"])({url:"".concat(s,"/save"),method:"post",data:t})},updateById:function(t){return Object(o["a"])({url:"".concat(s,"/update"),method:"put",data:t})},removeById:function(t){return Object(o["a"])({url:"".concat(s,"/remove/").concat(t),method:"delete"})},removeRows:function(t){return Object(o["a"])({url:"".concat(s,"/batchRemove"),method:"delete",data:t})},findAllList:function(){return Object(o["a"])({url:"".concat(s,"/findAllList"),method:"get"})}}},b7c5:function(t,e,a){"use strict";a("99af");var o=a("b775"),s="/admin/product/skuInfo";e["a"]={getPageList:function(t,e,a){return Object(o["a"])({url:"".concat(s,"/").concat(t,"/").concat(e),method:"get",params:a})},getById:function(t){return Object(o["a"])({url:"".concat(s,"/get/").concat(t),method:"get"})},save:function(t){return Object(o["a"])({url:"".concat(s,"/save"),method:"post",data:t})},updateById:function(t){return Object(o["a"])({url:"".concat(s,"/update"),method:"put",data:t})},removeById:function(t){return Object(o["a"])({url:"".concat(s,"/remove/").concat(t),method:"delete"})},removeRows:function(t){return Object(o["a"])({url:"".concat(s,"/batchRemove"),method:"delete",data:t})},publish:function(t,e){return Object(o["a"])({url:"".concat(s,"/publish/").concat(t,"/").concat(e),method:"get"})},check:function(t,e){return Object(o["a"])({url:"".concat(s,"/check/").concat(t,"/").concat(e),method:"get"})},isNewPerson:function(t,e){return Object(o["a"])({url:"".concat(s,"/isNewPerson/").concat(t,"/").concat(e),method:"get"})}}},d534:function(t,e,a){"use strict";var o=a("b775"),s="/admin/product/attr";e["a"]={getList:function(t){return Object(o["a"])({url:"".concat(s,"/").concat(t),method:"get"})},getById:function(t){return Object(o["a"])({url:"".concat(s,"/get/").concat(t),method:"get"})},save:function(t){return Object(o["a"])({url:"".concat(s,"/save"),method:"post",data:t})},updateById:function(t){return Object(o["a"])({url:"".concat(s,"/update"),method:"put",data:t})},removeById:function(t){return Object(o["a"])({url:"".concat(s,"/remove/").concat(t),method:"delete"})},removeRows:function(t){return Object(o["a"])({url:"".concat(s,"/batchRemove"),method:"delete",data:t})}}}}]);