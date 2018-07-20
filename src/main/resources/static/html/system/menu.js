new Vue({
	el:"#menuControl",
	data:{
		adddata:{},
		treeList:[],
		list:[],
		deleteid:'',
	},
	//初始化
	created:function(){
	    $('#menuControl').click(function(e){
        	var tag = e.srcElement || e.target;
        	if ( $(tag).is('.parent-tree') || $(tag).is('.treeview') || $(tag,'#treeView.treeview').size() > 0 ) return;
        	$('#treeView').slideUp();
        });
        this.loadList();
	},
	methods:{
	    treeViewCreate:function(){
            var that = this;

        	if ( $('#treeView').hasClass('treeView') ){
        		return;
        	}

        	$('#treeView').css({
        		position: 'absolute',
        		width: '100%',
        		left: '-13px',
        		zIndex: '100'
        	}).hide();


        	$('#treeView').treeview({data:[{id:"-1",text:"根菜单",nodes:that.treeList}],onNodeSelected:function(event, data) {
        		var d_ = $.extend({},that.adddata);
        		d_.parentId = data.id;
        		d_.parentName = data.text;
        		// 事件代码
        		that.adddata = d_;
        		$('#treeView').slideUp();
        	}});
        	$('#treeView').treeview('collapseAll')

        },
		saveData:function(){
			var that = this;
			Request.post(contextPath+'/api/system/menu/save',that.adddata,function(){
				that.loadList();
				that.closeModal();
			});
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
			Request.post(contextPath+'/api/system/menu/delete/'+that.deleteid,null,function(){
				$('#confirmModal').modal('hide');
				that.loadList();
				that.deleteid = '';
			});
		},
		toAdd:function(){
			this.adddata = {optionDesc:"添加菜单"};
			$('#addModal').modal('show');
			this.treeViewCreate();

		},
		toEdit:function(d){
			d.parentName = d.parentName || '根菜单';
			this.adddata = $.extend({},d);
			this.adddata.optionDesc="编辑菜单";
			this.adddata.nodes = [];
			$('#addModal').modal('show');
			this.treeViewCreate();
		},
		toSelect:function(){
			$('#treeView').slideDown()
		},
		hideSelect:function(){
			$('#treeView').slideToggle()
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
		loadList:function(){
            var that = this;
			//获取菜单
			Request.get(contextPath+"/api/system/menu/list",null,function(data){
				var newList = [];
				for (var i=0,d;d=data[i++];){
					newList.push(d);
					that.reverse(d.nodes,newList);
				}
				that.list = newList;
				that.treeList = data;
				setTimeout(function(){
					$('#menuTree').treegrid({
						'initialState': 'collapsed'
					});
				},500);
			});
		}
	}
});