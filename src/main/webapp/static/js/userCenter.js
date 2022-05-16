function getContextPath() {
    let pathName = document.location.pathname;
    let index = pathName.substr(1).indexOf("/");
    let result = pathName.substr(0, index + 1);
    return result;
}
let path = getContextPath();
let messageId = 0;
function index_btn() {
    window.location.href = path + "/";
}
$(function (){
    user_message();
    allAddress();
})

//获取用户信息回显
function user_message(){
    $("#uHead").empty();
    $.ajax({
        url:path+'/user',
        type:'get',
        success:function (result){
            let user = result.extend.user;
            let imgPath = path+'/static/image/'+user.uHead;
            $('<a href="javascript:;" class="thumbnail"></a>').append($('<img/>').attr('src',imgPath)).appendTo('#uHead');
            $('#uName').val(user.uName);
            $('input[type="radio"]').val([user.uGender]);
        }
    })
}
//修改用户信息
$('#updateUser').click(function (){
    $.ajax({
        type: "put",
        url: path+"/user/update",
        data: $('#userUpdate').serialize(),
        success:function (result){
            alert("修改成功！");
            user_message();
        }
    })
})
//调出增加地址模态框
$("#add-address").click(function () {
    reset_from("#add-Address-modal form");
    $('#add-Address-modal').modal({
        backdrop: "static"
    });
});
//表单样式重置
function reset_from(ele){
    $(ele)[0].reset();
    $(ele).find("*").removeClass("has-error has-success");
    $(ele).find(".help-block").text("");
}
//地址保存
$("#address-save-bte").click(function () {
    $.ajax({
        type: "POST",
        url: path+"/message/address",
        data: $('#add-Address-modal form').serialize(),
        success: function (result) {
            if (result.code == 100){
                allAddress();
                $("#add-Address-modal").modal('hide');
            }
        }
    });
})

//获取信息
function allAddress(){
    $.ajax({
        type: "get",
        url: path+"/message/address",
        success:function (result){
            build_address(result);
        }
    })
}

//构建收货地址
function build_address(result){
    $('#address-table tbody').empty();
    let addresss = result.extend.messages;
    $.each(addresss,function (){
        let mName = $('<td></td>').append(this.mName);
        let mAddress = $('<td></td>').append(this.mAddress);
        let mPhone = $('<td></td>').append(this.mPhone);
        let a1 = $('<a href="javascript:;" class="update-address">修改</a>');
        let a2 = $('<a href="javascript:;" class="delete-address">删除</a>');
        let caoZuo = $('<td></td>').append(a1).append('/').append(a2);
        let a = $('<a href="javascript:;" class="set-uMId">设为默认</a>')
        if (this.mId==result.extend.uMId){
            a.addClass('btn-info');
        }
        let moren = $('<td></td>').append(a);
        let tr = $('<tr></tr>').attr("mId",this.mId);
        tr.append(mName).append(mAddress).append(mPhone).append(caoZuo).append(moren).appendTo('#address-table tbody');
    })
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
//单个删除
$(document).on('click','.delete-address',function(){
    let mId = $(this).parents("tr").attr('mId');
    if (confirm("确认删除这个地址吗？")){
        $.ajax({
            url:path+"/message/address/"+mId,
            type:"DELETE",
            success:function (result) {
                allAddress();
            }
        })
    }
});
//调出修改地址模态框
$(document).on('click','.update-address',function(){
    let mId = $(this).parents("tr").attr('mId');
    messageId = mId;
    reset_from("#update-Address-modal form");
    getAddressOne(mId);
    $('#update-Address-modal').modal({
        backdrop: "static"
    });
})
//查询单个地址信息回显
function getAddressOne(mId) {
    $("#imgList").empty();
    $.ajax({
        url:path+"/message/address/"+mId,
        type:"GET",
        success:function (result) {
            $("#mAddress_update_input").val(result.extend.message.mAddress);
            $("#mName_update_input").val(result.extend.message.mName);
            $("#mPhone_update_input").val(result.extend.message.mPhone);
        }
    })
};
//地址修改
$("#address-update-btn").click(function () {
    $.ajax({
        type: "put",
        url: path+"/message/address/"+messageId,
        data: $('#update-Address-modal form').serialize(),
        success: function (result) {
            allAddress();
            $("#update-Address-modal").modal('hide');
        }
    });
})
//修改默认
$(document).on('click','.set-uMId',function(){
    let mId = $(this).parents("tr").attr('mId');
    $.ajax({
        url:path+'/user/update/'+mId,
        type:'put',
        success:function (result){
            allAddress();
        }
    })
})