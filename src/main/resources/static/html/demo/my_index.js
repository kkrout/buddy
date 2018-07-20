var LOCATION_HASH = {};
var pageTags;
var contextPath = "/buddy";

function js_uuid(){
    var uuid = new Date().getTime();
    var rdn = Math.floor(Math.random()*1000000);
    return uuid.toString(36)+"-"+rdn.toString(36);
}

(function(){
    var hash_ = window.location.hash;

    if ( hash_ ){
        var path;
        var param;
        var index = hash_.indexOf('?');
        if ( hash_.indexOf('?') > 0 ){
            path = hash_.substring(0,index);
            param = hash_.substring(index);
        }else{
            path = hash_;
        }

        LOCATION_HASH = {
            path:path,
            param:param||''
        };
    }else{
        LOCATION_HASH = {
            path:'#home.html',
            param:''
        };
    }
    pageTags = js_uuid();

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
                });
            },100);
        }
    });

})();


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
                    toastr.success('保存成功');
                }else{
                    errorback && errorback(res);
                    toastr.error('保存失败',null,{positionClass:"toast-top-center"});
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
