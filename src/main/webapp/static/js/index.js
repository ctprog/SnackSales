function getContextPath() {
    let pathName = document.location.pathname;
    let index = pathName.substr(1).indexOf("/");
    let result = pathName.substr(0, index + 1);
    return result;
}
let path = getContextPath();
let cId = 0;
//进入首页
$(function(){
    build_nav()
    allCommodity();
})
//导航栏构建
function build_nav(){
    $("#user-name-label").removeClass("hidden");
    $("#register_login").removeClass("hidden");
    isLogin(function (bool){
        if (bool){
            $("#register_login").addClass("hidden");
            getUserMassage();
        }else {
            $("#user-name-label").addClass("hidden");
        }
    })
}
//获取用户信息
function getUserMassage(){
    $.ajax({
        url:path+"/user",
        type:"get",
        success:function (result){
            if (result.code==100){
                let imagePath =path +"/static/image/"+ result.extend.user.uHead;
                $("<img class='img-circle'/>").attr("src",imagePath).attr("alt",result.extend.user.uHead).appendTo("#my");
                $("<span></span>").text(result.extend.user.uName).appendTo("#my");
            }
        }
    })
}
//是否已登录验证
function isLogin(callback){
    $.ajax({
        type: "get",
        url: path+"/user/isLogin",
        success: function (response) {
            if(response.code==100){
                callback(true)
            }else {
                callback(false)
            }
        }
    });
}
//分类栏构建
function build_cType(response){
    $("#nav_type_ul").empty();
    let types = response.extend.comTypes;
    let allCom = $("<li><a href='javascript:;' onclick='allCommodity()'><span class='glyphicon glyphicon-th-large' aria-hidden='true'></span> 全部商品</a></li>");
    allCom.appendTo("#nav_type_ul");
    $.each(types,function(index,item){
        let sep = $("<li role='separator' class='divider'></li>");
        let icon = $("<span class='glyphicon glyphicon-th-large' aria-hidden='true'></span>")
        let oneType = $("<li></li>").append($("<a href='javascript:;' onclick='someCommodity(this)' class='cType_btn'></a>").append(icon).append(" "+item.tName).attr("tId",item.tId))
        sep.appendTo("#nav_type_ul");
        oneType.appendTo("#nav_type_ul");
    })
}
//分类点击事件
$("#comTypes_btn").click(function (){
    $.ajax({
        url: path + "/commodityType",
        type: "GET",
        success: function (result) {
            build_cType(result);
        }
    })
})
//商品栏构建
function build_commodity(response){
    $("#main_commodity").empty();
    let commoditys = response.extend.commoditys;
    $.each(commoditys, function (index, item) { 
        let imagePath = path+"/static/image/"+item.imgs[0].iName;
        let image = $("<img />").attr("src",imagePath).attr("alt",item.cName);
        let com = $("<div class='caption'></div>").append($("<h3></h3>").text("￥"+item.cPrice)).append($("<p></p>").text(item.cName));
        let div1 = $("<div class='thumbnail'></div>").append(image).append(com);
        let div2 = $("<div class='col-xs-6 col-sm-4 col-md-3'></div>").append(div1);
        $("<a onclick='com_part_modal(this)'></a>").append(div2).attr("cId",item.cId).appendTo("#main_commodity");
    });
}
//获取全部商品
function allCommodity() {
    $.ajax({
        url: path + "/commodity",
        type: "GET",
        success: function (result) {
            build_commodity(result);
        }
    })
    return false;
}
//获取分类商品
function someCommodity(obj) {
    $.ajax({
        url: path + "/commodity/"+$(obj).attr("tId"),
        type: "GET",
        success: function (result) {
            build_commodity(result);
        }
    })
    return false;
}
//搜索商品
$("#search_btn").click(function(){
    let cName = $("#search_cName").val();
    $.ajax({
        url:path+"/commoditylike/"+cName,
        type:"GET",
        success:function(result){
            build_commodity(result);
        }
    })
})
//调出商品详情模态框
function com_part_modal(obj){
    cId = $(obj).attr("cId");

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
//加入购物车
$(".saveto-shoppingCar").click(function (){
    $.ajax({
        url:path+'/shoppingCar/'+cId,
        type:'post',
        success:function (result){
            if(result.code!=100){
                window.location.href=path+"/register";
            }else{
                alert("已加入购物车！");
                $("#com-part-modal").modal('hide');
            }
        }
    })
})
//退出
$("#quit").click(function (){
    if (confirm("确定要退出登录吗？")){
        $.ajax({
            url:path+"/user/quit",
            type:"get",
            success:function (result){
                build_nav();
            }
        })
    }
})