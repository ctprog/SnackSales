function getContextPath() {
    let pathName = document.location.pathname;
    let index = pathName.substr(1).indexOf("/");
    let result = pathName.substr(0, index + 1);
    return result;
}
let path = getContextPath();
let cmmmodityId = 0;

// 左导航栏点击事件
$("#left-nav li").click(function () {
    $("#left-nav li").removeClass("active");
    $(this).addClass("active");
    $(".list").removeClass("show hidden");
    $(".list").addClass("hidden");
    let id = $(this).attr("id");
    $('#' + id + '1').removeClass("hidden");
    $('#' + id + '1').addClass("show");
    switch (id) {
        case ("order"):
            left_order();
            break;
        case ("type"):
            left_type();
            break;
        case ("com"):
            left_com()
            break;
    }
});
// =============================================
//左导航栏订单管理
function left_order() {

}

// ============================================
//左导航栏分类管理
function left_type() {
    allType();
}
//构建分类表格
function buildComType(result){
    $("#type1 tbody").empty();
    let types = result.extend.comTypes;
    $.each(types,function (index,item){
        let tId = $("<td></td>").append(item.tId);
        let tName = $("<td></td>").append($("<input type='text' class='form-control'/>").attr("value",item.tName));
        let cNum = $("<td></td>").append(item.cNum);
        let update_btn = $("<button type='button' class='btn btn-info update_cType_btn'>修改</button>");
        let delete_btn = $("<button type='button' class='btn btn-danger delete_cType_btn'>删除</button>");
        let user = $("<td></td>").append($("<div class='btn-group' role='group'></div>").append(update_btn).append(delete_btn));
        $("<tr></tr>").append(tId).append(tName).append(cNum).append(user).appendTo("#type1 tbody");
    })
}
//获取全部分类
function allType(){
    $.ajax({
        url:path+"/commodityType",
        type:"GET",
        success:function (result){
            buildComType(result);
        }
    })
}
//分类输入框值校验
function cType_vai_tName(tName){
    let param = /^[a-zA-Z\u4e00-\u9fa5]{2,15}$/
    if (!param.test(tName)){
        return false;
    }
    return true;
}
//分类增加
$("#add_cType_input").click(function (){
    let tName = $("#type1 tfoot input").val();
    if (cType_vai_tName(tName)){
        $.ajax({
            url:path+"/commodityType",
            type:"POST",
            data:{'tName':tName},
            success:function (result){
                if (result.code==200){
                    alert("类型名称格式不正确！");
                } else {
                    alert("添加成功");
                    allType();
                    $("#type1 tfoot input").val("");
                }
            }
        })
    }else {
        alert("类型名称格式不正确！");
    }
})
//分类修改
$(document).on("click",".update_cType_btn",function (){
    let tName = $(this).parents("tr").find("td:eq(1)").children("input").val();
    let tId = $(this).parents("tr").find("td:eq(0)").text();
    if (cType_vai_tName(tName)){
        $.ajax({
            url:path+"/commodityType",
            type:"PUT",
            data:{'tName':tName,'tId':tId},
            success:function (result){
                if (result.code==200){
                    alert("类型名称格式不正确！");
                } else {
                    alert("修改成功");
                    allType();
                }
            }
        })
    }else {
        alert("类型名称格式不正确！");
    }
})
//分类删除
$(document).on("click",".delete_cType_btn",function (){
    let tId = $(this).parents("tr").find("td:eq(0)").text();
    if (confirm("删除分类将删除该分类下所有商品！是否确认删除？请谨慎操作！")){
        $.ajax({
            url:path+"/commodityType/"+tId,
            type:"delete",
            success:function (result){
                alert("删除成功！");
                allType();
            }
        })
    }
})
// ============================================
//左导航栏商品管理
function left_com() {
    getComTypes("#com1 select");
    allCommodity();
}
//商品分类构建
function getComTypes(ele) {
    $(ele).empty();
    if(ele == "#com1 select"){
        let option = $("<option></option>").append("全部商品").attr("value", "0");
        option.appendTo(ele);
    }
    $.ajax({
        url: path + "/commodityType",
        type: "GET",
        success: function (result) {
            let types = result.extend.comTypes;
            $.each(types, function (index,item) {
                let option = $("<option></option>").append(item.tName).attr("value", item.tId);
                option.appendTo(ele);
            });
        }
    })
}
//分类商品下拉change事件
function checkSelect(){
    if($("#com1 select").val()==0){
        allCommodity()
    }else{
        someCommodity("#com1 select")
    }
}
//搜索
$("#search_btn").click(function(){
    let cName = $("#com1 input").val();
    $.ajax({
        url:path+"/commoditylike/"+cName,
        type:"GET",
        success:function(result){
            buildCommdity(result);
        }
    })
})
//全部商品
function allCommodity() {
    $.ajax({
        url: path + "/commodity",
        type: "GET",
        success: function (result) {
            buildCommdity(result)
        }
    })
}
//分类商品
function someCommodity(obj) {
    $.ajax({
        url: path + "/commodity/"+$(obj).val(),
        type: "GET",
        success: function (result) {
            buildCommdity(result)
        }
    })
}
//构建商品列表
function buildCommdity(result) {
    $("#comsTable tbody").empty();
    let commoditys = result.extend.commoditys;
    $.each(commoditys, function (index,item) {
        let checkBoxId = $("<td><input type='checkbox' class='check_item' onclick='check_click(this)'/></td>")
        let comType = $("<td></td>").append(item.comType.tName);
        let cId = $("<td></td>").append(item.cId);
        let cName = $("<td></td>").append(item.cName);
        let cPrice = $("<td></td>").append(item.cPrice);
        let previewBtn = $("<button></button>").addClass("btn btn-primary btn-sm edit_btn").append("详情预览").attr("onclick", "com_part_modal(this)");
        let editBtn = $("<button></button>").addClass("btn btn-primary btn-sm edit_btn").append("编辑").attr("onclick", "edit_click(this)");
        let delBtn = $("<button></button>").addClass("btn btn-danger btn-sm delete_btn").append("删除").attr("onclick", "delete_click(this)");
        let btn = $("<td></td>").append(previewBtn).append(" ").append(editBtn).append(" ").append(delBtn);
        $("<tr></tr>").append(checkBoxId).append(comType).append(cId).append(cName).append(cPrice).append(btn).appendTo("#comsTable tbody");
    })
}
//调出增加商品模态框
$("#com_add_modal").click(function () {
    reset_from("#comAddModal form");
    getComTypes("#comAddModal select")
    $('#comAddModal').modal({
        backdrop: "static"
    });
});
//图片上传
$("#cImg_add_input").fileinput({
    language: 'zh',
    // uploadUrl: path + '/commodity',
    allowedFileExtensions: ['jpg', 'jpeg', 'png'], //接收的数据类型
    uploadAsync: false, //默认异步上传
    showUpload: false, //是否显示上传按钮
    showRemove: true, //显示移除按钮
    showPreview: true, //是否显示预览
    browseClass: "btn btn-info", //按钮样式
    dropZoneEnabled: false,
    minFileCount: 1,
    maxFileCount: 5,
});
//前端信息校验
function validate_add_from(){
    let cName = $("#cName_add_input").val();
    if (cName == ""||$.trim(cName).length==0){
        show_validate_msg("#cName_add_input","error","商品名称不能为空！");
        return false;
    } else {
        show_validate_msg("#cName_add_input","success","");
    }

    let cPrice = $("#cPrice_add_input").val();
    let regcPrice = /(^0\.[1-9]\d?$)|(^0\.\d[1-9]?$)|(^[1-9]\d{0,7}(\.\d{0,2})?$)/
    if (!regcPrice.test(cPrice)){
        show_validate_msg("#cPrice_add_input","error","价格格式不合法！");
        return false;
    } else {
        show_validate_msg("#cPrice_add_input","success","");
    }
    return true;
}
//校验信息显示
function show_validate_msg(ele,status,msg){
    $(ele).parent().removeClass("has-success has-error");
    $(ele).next("span").text("");
    if ("success"==status){
        $(ele).parent().addClass("has-success");
        $(ele).next("span").text(msg);
    } else if ("error"==status) {
        $(ele).parent().addClass("has-error");
        $(ele).next("span").text(msg);
    }
}
//表单样式重置
function reset_from(ele){
    $(ele)[0].reset();
    $(ele).find("*").removeClass("has-error has-success");
    $(ele).find(".help-block").text("");
}
//商品保存
$("#com_save_btn").click(function () {
    if (!validate_add_from()){
        return false;
    }
    if ($(this).attr("ajax-va")=="error"){
        return false;
    }
    let formData = new FormData($("#comAddModal form")[0]);
    $.ajax({
        type: "POST",
        url: path+"/commodity",
        data: formData,
        contentType: false,
        processData: false,
        success: function (result) {

            if (result.code == 100){
                allCommodity();
                $("#comAddModal").modal('hide');
            } else{
                if (undefined !=result.extend.error.imgs){
                    show_validate_msg("#cImg_add_input","error",result.extend.error.imgs);
                }
                if (undefined !=result.extend.error.cName){
                    show_validate_msg("#cName_add_input","error",result.extend.error.cName);
                }
            }
        }
    });
})
//单个删除
function delete_click(obj) {
    let path = getContextPath();
    let cName = $(obj).parents("tr").find("td:eq(3)").text();
    if (confirm("确认删除【"+cName+"】吗？")){
        $.ajax({
            url:path+"/commodity/"+$(obj).parents("tr").find("td:eq(2)").text(),
            type:"DELETE",
            success:function (result) {
                allCommodity();
            }
        })
    }
};
//全选全不选
$("#check_all").click(function () {
    $(".check_item").prop("checked",$(this).prop("checked"));
});
function check_click(obj) {
    let flag = $(".check_item:checked").length==$(".check_item").length;
    $("#check_all").prop("checked",flag)
};
//点击批量删除
$("#com_delete_modal").click(function () {
    let path = getContextPath();
    let cNames = "";
    let del_idstr = "";
    $.each($(".check_item:checked"),function () {
        cNames += $(this).parents("tr").find("td:eq(3)").text()+",";
        del_idstr += $(this).parents("tr").find("td:eq(2)").text()+"-";
    })
    cNames = cNames.substring(0,cNames.length-1);
    del_idstr = del_idstr.substring(0,del_idstr.length-1);
    if ($(".check_item:checked").length==0){
        return false;
    };
    if (confirm("确定要删除【"+cNames+"】吗？")){
        $.ajax({
            url:path+"/commodity/"+del_idstr,
            type:"DELETE",
            success:function (result) {
                $("#check_all").prop("checked",false);
                alert(result.msg);
                allCommodity();
            }
        })
    };
})
//图片修改
$("#cImg_update_input").fileinput({
    language: 'zh',
    // uploadUrl: path + '/commodity',
    allowedFileExtensions: ['jpg', 'jpeg', 'png'], //接收的数据类型
    uploadAsync: false, //默认异步上传
    showUpload: false, //是否显示上传按钮
    showRemove: true, //显示移除按钮
    showPreview: true, //是否显示预览
    browseClass: "btn btn-info", //按钮样式
    dropZoneEnabled: false,
    minFileCount: 1,
    maxFileCount: 5,
});
//调出修改商品模态框
function edit_click(obj){
    commodityId = $(obj).parents("tr").find("td:eq(2)").text();
    reset_from("#comUpdateModal form");
    getcommdityOne(commodityId);
    getComTypes("#comUpdateModal select");
    $('#comUpdateModal').modal({
        backdrop: "static"
    });
}
//查询单个商品信息回显
function getcommdityOne(cid) {
    $("#imgList").empty();
    $.ajax({
        url:path+"/commodityOne/"+cid,
        type:"GET",
        success:function (result) {
            $("#cName_update_input").val(result.extend.commodity.cName);
            $("#cPrice_update_input").val(result.extend.commodity.cPrice);
            $("#cType_update_select").val([result.extend.commodity.tId]);
            let imgs = result.extend.commodity.imgs;
            $.each(imgs,function(index,item){
                let imgPath = path + "/static/image/" + item.iName;
                let d = $("<div class='col-xs-6 col-md-3'></div>").append($("<a id='deleteImg' class='thumbnail'></a>").append($("<img/>").attr("src",imgPath).attr("alt",item.iName)));
                d.appendTo("#imgList");
            })
        }
    })
};
//回显商品图片
function showImg(cid) {
    $("#imgList").empty();
    $.ajax({
        url:path+"/commodityOne/"+cid,
        type:"GET",
        success:function (result) {
            let imgs = result.extend.commodity.imgs;
            $.each(imgs,function(index,item){
                let imgPath = path + "/static/image/" + item.iName;
                let d = $("<div class='col-xs-6 col-md-3'></div>").append($("<a id='deleteImg' class='thumbnail'></a>").append($("<img/>").attr("src",imgPath).attr("alt",item.iName)));
                d.appendTo("#imgList");
            })
        }
    })
};
//删除单张图片
$(document).on("click","#deleteImg",function () {
    let a = $(this).find("img:eq(0)").attr("alt");
    if (confirm("确认删除这张图片吗？")){
        $.ajax({
            url:path+"/img",
            type:"DELETE",
            data: {"iName":a},
            success:function (result) {
                showImg(commodityId);
            }
        })
    }
    return false;
});
//商品修改
$("#com_update_btn").click(function () {
    let cName = $("#cName_update_input").val();
    if (cName == ""||$.trim(cName).length==0){
        show_validate_msg("#cName_update_input","error","商品名称不能为空！");
        return false;
    } else {
        show_validate_msg("#cName_update_input","success","");
    }

    let cPrice = $("#cPrice_update_input").val();
    let regcPrice = /(^0\.[1-9]\d?$)|(^0\.\d[1-9]?$)|(^[1-9]\d{0,7}(\.\d{0,2})?$)/
    if (!regcPrice.test(cPrice)){
        show_validate_msg("#cPrice_update_input","error","价格格式不合法！");
        return false;
    } else {
        show_validate_msg("#cPrice_update_input","success","");
    }
    let formData = new FormData($("#comUpdateModal form")[0]);
    $.ajax({
        type: "POST",
        url: path+"/commodity/"+commodityId,
        data: formData,
        async:false,
        cache:false,
        contentType: false,
        processData: false,
        success: function (result) {
            allCommodity();
            $("#comUpdateModal").modal('hide');
        }
    });
})
//调出商品详情模态框
function com_part_modal(obj){
    let cId = $(obj).parents("tr").find("td:eq(2)").text();
    $.ajax({
        url:path+"/commodityOne/"+cId,
        type:"GET",
        success:function (result) {
            build_com_part(result);
            $('#com-part-modal').modal({
                backdrop: "static"
            })
        }
    })
}
//构建商品详情
function build_com_part(result) {
    $("#com_images").empty();
    $("#com_part").empty();
    let com = result.extend.commodity;
    let imgs =com.imgs;
    $.each(imgs,function (index,item){
        let imagePath = path+"/static/image/"+item.iName;
        let img = $("<img/>").attr("src",imagePath);
        let one = $("<div class='item'></div>").append(img);
        if (index==0){
            one.addClass("active");
        }
        one.appendTo("#com_images");
    })
    $("<h2>咕噜零食铺</h2>").appendTo("#com_part");
    $("<p></p>").text(com.cName).appendTo("#com_part");
    $("<p></p>").append($("<a class='btn btn-danger btn-lg' role='button'></a>").text('￥'+com.cPrice+'元')).appendTo("#com_part");
};
// ============================================