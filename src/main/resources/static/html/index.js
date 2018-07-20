var LOCATION_HASH = {};
var pageTags;
var contextPath = "/buddy";

(function(){

    var _menuList = new Vue({
        el:"#menuList",
        data:{
            currentPage:LOCATION_HASH.path,
            list:[]
         },
        methods:{
        }
    });

    var activeMenu = function(parent,hash){
        if ( $('.submenu',parent).size() == 0  ){
            return;
        }
        var find = false;
        $('.submenu>li',parent).each(function(){
            var url = $('a',this).attr('href')
            var current = $(this);
            if ( url == hash){
                current.addClass('active');
                //设置父级打开状态
                parent.addClass('active open');
                find = true;
                //跳出循环
                return false;
            }
            if ( activeMenu(current,hash) ){
                parent.addClass('active open');
                //设置父级打开状态
                find = true;
                //跳出循环
                return false;
            }
        });
        return find;
    }

    $.ajax({
        url:contextPath+"/api/system/menu/list",
        success:function(data){
            _menuList.list = data;
             $('#menuList').fadeIn();
            setTimeout(function(){
                $.history.init(function(hash) {
                    var href = "#"+hash;

                    /**************设置栏目激活状态 begin*************/
                    $('#menuList li.active').removeClass('active').removeClass('open');
                    $('#menuList>li').each(function(){
                        var url = $('a',this).attr('href')
                        var parent = $(this);
                        if ( url == href){
                            parent.addClass('active');
                            //跳出循环
                            return false;
                        }
                        if ( activeMenu(parent,href) ){
                            parent.addClass('active');
                            //跳出循环
                            return false;
                        }
                    });
                    hash && $('#pageContent').load(contextPath+"/static/html/"+hash);
                    /**************设置栏目激活状态 end*************/

                    /**************设置面包屑 begin*************/
                    $('#breadcrumb').empty();
                    $('#menuList li.active').each(function(i){
                        var breadA = $(this).children('a');
                        var breadName = $.trim(breadA.text());
                        var breadIcon = (breadA.children('i')[0] || '') && breadA.children('i')[0].className;
                        var $li = $('<li>');

                        if( breadIcon && breadIcon != 'icon-double-angle-right' ){
                           $('<i>').addClass(breadIcon).appendTo($li);
                        }

                        if ( i == $('#menuList li.active').size() -1 ){
                            $li.addClass('active');
                            $li.text(breadName);
                         }else{
                            $('<a href="javascript:void(0)" >').text(breadName).appendTo($li);
                         }

                        $li.appendTo('#breadcrumb');
                    });
                    /**************设置面包屑 end*************/
                });
            },100);
        }
    });

})();

/** 请求组件封装*/
var Request = {
    post:function(url,data,callback,errorback){
        $.ajax({
            url:url,
            data:JSON.stringify(data),
            contentType: 'application/json;charset=utf-8',
            method:"post",
            success:function(res){
                if ( res.status == "OK" ){
                    callback && callback(res);
                    toastr.success('操作成功');
                }else{
                    errorback && errorback(res);
                    toastr.error('操作失败',null,{positionClass:"toast-top-center"});
                }

            }
        });
    },
    get:function(url,data,callback){
        $.ajax({
            url:url,
            data:data,
            method:"get",
            success:function(res){
                callback && callback(res);
            }
        });
    },
    getNoLoading:function(url,callback){
        $.ajax({
            url:url,
            data:this.adddata,
            method:"post",
            beforeSend:$.noop,
            success:function(res){
                callback && callback(res);
            },
            error:$.noop,
            complete:$.noop
        });
    }
};


function loadingDefault(msg) {
    $('body').loading({
        loadingWidth:240,
        title:'加载中...',
        name:'GoableLoading',
        discription:msg||'',
        direction:'column',
        type:'origin',
        // originBg:'#71EA71',
        originDivWidth:40,
        originDivHeight:40,
        originWidth:6,
        originHeight:6,
        smallLoading:false,
        loadingMaskBg:'rgba(0,0,0,0.2)'
    });
}

function hideLoadding(){
    removeLoading('GoableLoading');
}

/** ajax配置 */
$.ajaxSetup({
    dataType:"json",
    beforeSend:function(){
        loadingDefault();
    },
    complete:function(){
        hideLoadding();
    },
    error:function(res){
        var rs = res.responseJSON;
        if ( rs && rs.errorCode ){
            toastr.error(rs.errorMsg,null,{positionClass:"toast-top-center"});
        }

    }
});
/** 初始化消息框配置 */
toastr.options = {
    closeButton: false,
    debug: false,
    progressBar: true,
    positionClass: "toast-bottom-center",
    onclick: null,
    showDuration: "300",
    hideDuration: "1000",
    timeOut: "2000",
    extendedTimeOut: "1000",
    showEasing: "swing",
    hideEasing: "linear",
    showMethod: "fadeIn",
    hideMethod: "fadeOut"
};