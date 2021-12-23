function getContextPath() {
    let pathName = document.location.pathname;
    let index = pathName.substr(1).indexOf("/");
    let result = pathName.substr(0, index + 1);
    return result;
}
let path = getContextPath();
function index_btn() {
    window.location.href = path + "/";
}
function userCenter_btn() {
    window.location.href = path + "/userCenter";
}
$(function (){
    getAllOrder();
});
function getAllOrder(){
    $.ajax({
        url:path+"/order/select",
        type:'get',
        success:function (result){
            build_orders(result);
        }
    })
}
function build_orders(result){
    let orders = result.extend.orders;
    $.each(orders,function (index,item){
        let orderCom = item.orderCom;
        let time = getMyDate(item.oStartTime);
        let msg = item.message.mName+"——"+item.message.mAddress+"——"+item.message.mPhone
        let oId = $('<div class="col-xs-3 col-sm-3 col-md-3"></div>').text('订单号：'+item.oId);
        let startTime = $('<div class="col-xs-3 col-sm-3 col-md-3"></div>').text('提交时间：'+time);
        let messsage = $('<div class="col-xs-6 col-sm-6 col-md-6"></div>').text('收货信息：'+msg);
        let orderHead = $('<div class="panel-heading clearfix"></div>').append(oId).append(startTime).append(messsage);
        let orderBody = $('<div class="panel-body orderBody"></div>');
        let coms = $('<div class="col-xs-6 col-sm-6 col-md-6"></div>');
        $.each(orderCom,function (index,oneOrderCom){
            let com = oneOrderCom.commodity;
            let imgPath =path +"/static/image/"+ com.imgs[0].iName;
            let img = $('<div class="col-xs-3 col-sm-3 col-md-3"></div>').append($('<a href="javascript:;" class="thumbnail"></a>').append($('<img/>').attr('src',imgPath)));
            let cName = $('<div class="col-xs-9 col-sm-9 col-md-9"></div>').text(com.cName);
            $('<div class="row"></div>').append(img).append(cName).appendTo(coms);
        })
        coms.appendTo(orderBody);
        $('<div class="col-xs-3 col-sm-3 col-md-3"></div>').append($('<h4></h4>').text('￥'+item.allPrice)).appendTo(orderBody);
        $('<div class="col-xs-3 col-sm-3 col-md-3"></div>').append($('<h4></h4>').text(item.oState)).appendTo(orderBody);
        $('<div class="panel panel-default"></div>').append(orderHead).append(orderBody).appendTo('#order_body');
    })
}

function getMyDate(str){
    let oDate = new Date(str),
        oYear = oDate.getFullYear(),
        oMonth = oDate.getMonth()+1,
        oDay = oDate.getDate(),
        oHour = oDate.getHours(),
        oMin = oDate.getMinutes(),
        oSen = oDate.getSeconds(),
        oTime = oYear +'-'+ getzf(oMonth) +'-'+ getzf(oDay) +' '+ getzf(oHour) +':'+ getzf(oMin) +':'+getzf(oSen);//最后拼接时间
    return oTime;
};
//补0操作
function getzf(num){
    if(parseInt(num) < 10){
        num = '0'+num;
    }
    return num;
}