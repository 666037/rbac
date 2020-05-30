var loadingIndex;
window.showLoading = function () {
    loadingIndex = layer.load(1, { shade: [0.5, '#393D49'] });
}
window.hideLoading = function () {
    layer.close(loadingIndex);
}
window.redirect = function (url) {
    window.location.href = url;
}

window.layerMsg = function (msg) {
    //layer.msg(msg, { icon: 5 });
    if (msg == null || msg == undefined) {
        msg = "错误";
    }
    if (msg.length == 0) {
        return;
    }
    layer.msg(msg);
}


//重写$.ajax












// 以下代码是配置layui扩展模块的目录，每个页面都需要引入



layui.config({
    version: '317',
    base: getProjectUrl() + 'assets/module/'
}).extend({
    steps: 'steps/steps',
    notice: 'notice/notice',
    cascader: 'cascader/cascader',
    dropdown: 'dropdown/dropdown',
    fileChoose: 'fileChoose/fileChoose',
    treeTable: 'treeTable/treeTable',
    Split: 'Split/Split',
    Cropper: 'Cropper/Cropper',
    tagsInput: 'tagsInput/tagsInput',
    citypicker: 'city-picker/city-picker',
    introJs: 'introJs/introJs',
    zTree: 'zTree/zTree',
    iconPicker: 'iconPicker/iconPicker'
}).use(['layer', 'admin'], function () {
    var $ = layui.jquery;
    var layer = layui.layer;
    var admin = layui.admin;

    // 移除loading动画
    setTimeout(function () {
        admin.removeLoading();
    }, window === top ? 300 : 0);
    (function ($) {
        //首先备份下jquery的ajax方法
        var _ajax = $.ajax;
        //重写jquery的ajax方法
        $.ajax = function (opt) {
            //备份opt中error和success方法
            var fn = {
                error: function (XMLHttpRequest, textStatus, errorThrown) { },
                success: function (data, textStatus) { }
            }
            if (opt.error) {
                fn.error = opt.error;
            }
            if (opt.success) {
                fn.success = opt.success;
            }
            var _opt = $.extend(opt, {
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    hideLoading();//必须放在这里，不能放在complete
                    var obj = XMLHttpRequest.responseJSON;
                    if (XMLHttpRequest.status == 403) {

                        if (obj && obj.code == 1002) {
                            redirect("/rbac/login?returnUrl=" + window.location);
                        }
                        return;
                    } else if (XMLHttpRequest.status == 404) {

                            layer.alert(opt.url+ " 该接口不存在");

                    }
                    fn.error(XMLHttpRequest, textStatus, errorThrown);
                },
                success: function (data, textStatus) {

                    hideLoading();//必须放在这里，不能放在complete
                    if (data.IsSuccess != undefined && !data.IsSuccess) {
                     //do something
                    }

                    fn.success(data, textStatus);
                },
                beforeSend: function (XHR) {

                    if (opt.showLoading == undefined || opt.showLoading == true) {
                        showLoading();
                    }
                }
            });
            return _ajax(_opt);
        };
    })(layui.jquery);
});

// 获取当前项目的根路径，通过获取layui.js全路径截取assets之前的地址
function getProjectUrl() {
    var layuiDir = layui.cache.dir;
    if (!layuiDir) {
        var js = document.scripts, last = js.length - 1, src;
        for (var i = last; i > 0; i--) {
            if (js[i].readyState === 'interactive') {
                src = js[i].src;
                break;
            }
        }
        var jsPath = src || js[last].src;
        layuiDir = jsPath.substring(0, jsPath.lastIndexOf('/') + 1);
    }
    return layuiDir.substring(0, layuiDir.indexOf('assets'));
}