var all_menus = [
			{"menuid":"2","icon":"icon-printing-head","menuname":"印花管理",
				"menus":[
				        {"menuid":"22","menuname":"印花分类列表","icon":"icon-printingcategory","url":"/printing/category/page/index?menuCode=\printing/category"},
						{"menuid":"24","menuname":"印花列表","icon":"icon-printing","url":"/printing/page/index?menuCode=printing"},
					]
			},
			{"menuid":"4","icon":"icon-commodity-head","menuname":"商品管理",
				"menus":[
				        {"menuid":"42","menuname":" 商品分类列表","icon":"icon-commoditycategory","url":"/commodity/category/page/index?menuCode=\commodity/category"},
						{"menuid":"44","menuname":" 商品列表","icon":"icon-commodity","url":"/commodity/page/index?menuCode=commodity"}
					]
			},
			{"menuid":"6","icon":"icon-store-head","menuname":"门店管理",
				"menus":[
				        {"menuid":"62","menuname":"门店列表","icon":"icon-store-list","url":"/store/page/index?menuCode=store"},
					]
			},
			{"menuid":"8","icon":"icon-finished-head","menuname":"成品展示",
				"menus":[
				         {"menuid":"82","menuname":" 成品列表","icon":"icon-finished","url":"/finished/page/index?menuCode=finished"}
					]
			},
			{"menuid":"10","icon":"icon-ipad","menuname":"设备管理",
				"menus":[
				         {"menuid":"102","menuname":" iPad管理","icon":"icon-ipad","url":"/deviceInfo/page/index?menuCode=deviceInfo"}
					]
			},
			{"menuid":"12","icon":"icon-management-message","menuname":"消息管理",
				"menus":[
					{"menuid":"122","menuname":" 群发消息","icon":"icon-group-message","url":"/groupmessage/page/index?menuCode=groupmessage"},
					{"menuid":"124","menuname":" 图文记录","icon":"icon-history-record","url":"/news/page/index?menuCode=news"},
					{"menuid":"126","menuname":" 文本记录","icon":"icon-history-record","url":"/text/page/index?menuCode=text"},
					{"menuid":"128","menuname":" 图片记录","icon":"icon-history-record","url":"/images/page/index?menuCode=images"},
					{"menuid":"1210","menuname":" 模板列表","icon":"icon-history-record","url":"/template/page/index?menuCode=/pn/template"}
					]
			},
			{"menuid":"14","icon":"icon-management-message","menuname":"微信管理",
				"menus":[
					{"menuid":"142","menuname":" 二维码管理","icon":"icon-qr-list","url":"/wx/qr?menuCode=\wx/qr"},
					{"menuid":"144","menuname":" 粉丝列表","icon":"icon-commodity","url":"/pn/fans/index"},
					{"menuid":"146","menuname":" 卡券管理","icon":"icon-group-message","url":"/pn/mcard/index"},
					{"menuid":"142","menuname":" 二维码管理","icon":"icon-group-message","url":"/wx/qr?menuCode=wx/qr"},
					{"menuid":"122","menuname":" 粉丝列表","icon":"icon-commodity","url":"/fansInfo/page/index?menuCode=/pn/fans"}
					]
			},
			{"menuid":"16","icon":"icon-management-message","menuname":"素材管理",
				"menus":[
					{"menuid":"162","menuname":" 图文素材","icon":"icon-qr-list","url":"/pn/imagetextmaterial"},
					{"menuid":"164","menuname":" 图片素材","icon":"icon-commodity","url":"/pn/imagematerial"}
					]
			}

		];

var dataList = [{"id":"2c90d0365408361c015409312d050001","catNo":"001","catName":"Home / 家居","catPno":"0","catPath":"001","sortNo":1,"description":"一级分类","deleteFlag":0,"createTime":"2016-04-12 14:38:18","updateTime":"2016-04-13 10:38:29"},{"id":"2c90d0365408361c015409327b910004","catNo":"002","catName":"Kids / 婴童","catPno":"0","catPath":"002","sortNo":2,"description":"一级分类","deleteFlag":0,"createTime":"2016-04-12 14:39:44","updateTime":"2016-04-13 10:30:36"},{"id":"2c90d0365408361c01540932c1090006","catNo":"003","catName":"Men / 男士","catPno":"0","catPath":"003","sortNo":3,"description":"一级分类","deleteFlag":0,"createTime":"2016-04-12 14:40:01","updateTime":"2016-04-13 10:45:37"},{"id":"2c90d0365408361c0154093337ed0008","catNo":"004","catName":"Woman / 女士","catPno":"0","catPath":"004","sortNo":4,"description":"一级分类","deleteFlag":0,"createTime":"2016-04-12 14:40:32","updateTime":"2016-04-13 10:46:38"},{"id":"4028479e5b04718c015b1eccdeeb4fde","catNo":"005","catName":"Other / 其他","catPno":"0","catPath":"005","sortNo":5,"description":"其他","deleteFlag":0,"createTime":"2017-03-30 18:39:55","updateTime":"2017-03-30 18:40:18"}];


var initFunaction = function(){

    $('#main-menu').metisMenu();

    $(window).bind("load resize", function () {
        if ($(this).width() < 768) {
            $('div.sidebar-collapse').addClass('collapse')
        } else {
            $('div.sidebar-collapse').removeClass('collapse')
        }
    });
}

var app = angular.module('indexApp',[]).controller('indexAppControl', function($scope, $http) {
    $scope.menuList = all_menus;

    $scope.tableList = dataList;

    $('#page-inner').load("page/commo.html");


});

app.directive('repeatFinish',function($timeout){
    return {
        link: function(scope,element,attr){
            if(scope.$last == true){
                console.log('设置手风琴...');
                /*MENU  ------------------------------------*/
                $timeout(function(){
                    initFunaction();
                });
            }
        }
    }
});