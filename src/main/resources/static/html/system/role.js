new Vue({
	el:"#roleControl",
	data:{
		adddata:{},
		treeList:[],
		list:[],
		deleteid:'',
		checkList:[]
	},
	//dom初始化
	created:function(){
        this.loadList();
	},
	methods:{
		saveData:function(){
			var that = this;
			//获取菜单节点
			var checkList = $('#treeView').treeview('getChecked');
            var menus = [];
            this.reverse(checkList,menus,'id');
			that.adddata.menus = menus;
			Request.post(contextPath+'/api/system/role/save',that.adddata,function(){
				that.loadList();
				that.closeModal();
			});
		},
		reverse:function(nodes,list,prop){
            if ( nodes && nodes.length > 0 ){
                for (var i_ =0,d_;d_= nodes[i_++];){
                    if ( prop )
                        list.push(d_[prop])
                    else
                        list.push(d_);
                    this.reverse(d_.nodes,list,prop)
                }
            }
        },
		closeModal:function(){
			this.adddata = {};
			$('#addModal').modal('hide')
		},
		toDelete:function(d){
			this.deleteid = d.id;
			$('#confirmModal').modal('show');
		},
		deleteData:function(){
			var that = this;
			Request.post(contextPath+'/api/system/role/delete/'+that.deleteid,null,function(){
				$('#confirmModal').modal('hide');
				that.loadList();
				that.deleteid = '';
			});
		},
		toAdd:function(){
			this.adddata = {optionDesc:"添加角色"};
			$('#addModal').modal('show');
			this.treeViewCreate();

		},
		toEdit:function(d){
			this.adddata = $.extend({},d);
			this.adddata.optionDesc="添加角色";
			$('#addModal').modal('show');

            this.treeViewCreate();


		},
		toSelect:function(){
			$('#treeView').slideDown()
		},
		hideSelect:function(){
			$('#treeView').slideToggle()
		},
		collapseTree:function(e){
            var areaSet = $('#treeView').offset();
            var areaWidth = $('#treeView').width();
            var areaHeight = $('#treeView').height();
            if (
                e.x > areaSet.left && e.x < areaWidth + areaSet.left
                &&
                e.y > areaSet.top && e.y < areaHeight + areaSet.top
            ){
                //treeView区域内
                //console.log("treeView区域内");
            }else{
                $('#treeView').treeview('collapseAll')
            }
		},
		checkParent:function(target,expr){
            if ( $(target).parent().is(expr) ){
                return true;
            }
            return this.checkParent($(target).parent(),expr);
		},
		loadList:function(){
            var that = this;
			//获取菜单
			Request.get(contextPath+"/api/system/menu/list",null,function(data){
				that.treeList = data;
			});

			//获取角色
			Request.get(contextPath+"/api/system/role/list",null,function(data){
				that.list = data;
			});
		},
		findArray:function(ary,v){
            $.each(ary,function(){
                return v = this;
            })
		},
		initCheckTree:function(values,treeList){
            var that = this;
		    if ( !values || !treeList || values.length == 0 || treeList.length == 0 ) return;

		    $.each(treeList,function(){
                if ( $.inArray(this.id,values) != -1 ){
                    this.state = this.state || {};
                    this.state.checked =true;
                }else{
                    this.state = this.state || {};
                    this.state.checked = false;
                }
                that.initCheckTree(values,this.nodes);
		    });
		},
		treeViewCreate:function(){
		    var that = this;
            //	if ( $('#treeView').hasClass('treeView') ){
            //		return;
            //	}

            //选中菜单节点
            this.initCheckTree(this.adddata.menus,this.treeList);

            $('#treeView').css({
                position: 'absolute',
                width: '100%',
                left: '-13px',
                zIndex: '100'
            });


            $('#treeView').treeview({
                data:that.treeList,
                showCheckbox:true,
                showTags:true,
                onNodeChecked:function(event, data) {
                    var parentNode = $(this).treeview('getParent',data);
                    if ( parentNode && parentNode.id ){
                        $(this).treeview('checkNode',parentNode);
                    }
                },
                onNodeUnchecked:function(event, data){
                    var that = this;
                    if ( data.nodes && data.nodes.length > 0 ){
                        for (var i_=0,d_;d_=data.nodes[i_++];){
                            $('#treeView').treeview('uncheckNode',$('#treeView').treeview('getNode',d_.nodeId));
                        }
                    }
                }
            });
            $('#treeView').treeview('collapseAll')
		}
	}
});