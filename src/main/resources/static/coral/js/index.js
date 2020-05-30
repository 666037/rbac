(function ($) {
    $.learunindex = {
        jsonWhere: function (data, action) {
            if (action == null) return;
            var reval = new Array();
            $(data).each(function (i, v) {
                if (action(v)) {
                    reval.push(v);
                }
            })
            return reval;
        },
        makeHtml: function (datas) {
            // alert(_html+"=data.child===>"+_html)
            return get_html(datas);
        },
        loadMenu: function () {
            var mdata  ="";
            $.ajax({
                type: "get",
                url: "/rbac/right/getTreeMenuList",
                async:false, // 异步请求
                cache:false, // 设置为 false 将不缓存此页面
                dataType: 'json', // 返回对象
                success: function(res) {
                    if(res.code == 0){
                        mdata  = res.result;
                        if(mdata == "" || mdata == null || mdata.length == 0){

                        }
                    }else{
                        if(mdata == "" || mdata == null || mdata.length == 0){
                            mdata = menusData_def;
                        }
                    }
                },
                error: function(res) {
                    // 请求失败函数
                    console.log(res);
                    if(mdata == "" || mdata == null || mdata.length == 0){
                        mdata = menusData_def;
                    }
                }
            })
            var left_html =  get_html(mdata)
            $("#sidebar-menu").append(left_html);
        }
    };
    $(function () {
        $.learunindex.loadMenu();
    });
})(jQuery);


function get_html(datas) {
    var _html = "";
    for (var i=0;i<datas.length;i++){
        var data = datas[i];
        if(data.pid == 0){
            _html += '<li class="layui-nav-item">';
            //如果没有子对象
            if(!data.children||(data.children&&data.children.length==0)){
                _html += '<a lay-href="'+data.rightUrl+'">';
                _html += '<i class="layui-icon '+data.rightIcon+'"></i>&emsp;';
                _html += '<cite>'+data.rightName+'</cite>';
                _html += '</a>';
            }else{
                //如果有子菜单
                _html += '<a>';
                _html += '<i class="layui-icon '+data.rightIcon+'"></i>&emsp;';
                _html += '<cite>'+data.rightName+'</cite>';
                _html += '</a>';
                _html += '<dl class="layui-nav-child">';
                _html += get_html(data.children);
                _html += '</dl>';
            }
            _html += '</li>';
        }else{
            if(!data.children||(data.children&&data.children.length==0)){
                _html += '<dd>';
                _html += '<a lay-href="'+data.rightUrl+'">';
                _html += '<i class="layui-icon '+data.rightIcon+'"></i>&emsp;';
                _html += '<cite>'+data.rightName+'</cite>';
                _html += '</a>';
                _html += '</dd>';
            }else{
                //如果有子菜单
                _html += '<dd>';
                _html += '<a lay-href="">';
                _html += '<i class="layui-icon '+data.rightIcon+'"></i>&emsp;';
                _html += '<cite>'+data.rightName+'</cite>';
                _html += '</a>';
                _html += '<dl class="layui-nav-child">';
                _html += get_html(data.children);
                _html += '</dl>';
                _html += '</dd>';
            }
        }
    }
    return _html;
}