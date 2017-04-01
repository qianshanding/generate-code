<#assign base=request.contextPath />
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Hold-All</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/dist/css/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="/dist/css/ionicons/css/ionicons.min.css">
    <link rel="stylesheet" href="/dist/main.min.css">
    <link rel="stylesheet" href="/plugins/iCheck/flat/_all.css">
    <link rel="stylesheet" href="/dist/css/skins/skin-green.min.css">
</head>
<body class="hold-transition skin-green sidebar-mini">
<div class="wrapper">
<#include "common/header.ftl"/>

<#include "common/left.ftl" />

    <div class="content-wrapper">
        <section class="content-header">
            <h1>
                系统工具
                <small>开发相关</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="/"><i class="fa fa-dashboard"></i> 主页</a></li>
                <li class="active">Dashboard</li>
            </ol>
        </section>

        <section class="content">
            <div class="row">
                <div class="col-md-8">
                    <div class="box box-success">
                        <div class="box-header with-border">
                            <h3 class="box-title">代码生成</h3>
                        </div>
                        <form id="generateCode" class="form-horizontal">
                            <div class="box-body">
                                <div class="form-group">
                                    <label for="ip" class="col-sm-2 control-label">数据库IP</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" name="databaseIp"
                                               placeholder="127.0.0.1">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="port" class="col-sm-2 control-label">数据库名称</label>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" name="database" placeholder="auto">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="port" class="col-sm-2 control-label">端口</label>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" name="port" placeholder="3306">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="userName" class="col-sm-2 control-label">用户名</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" name="userName" placeholder="root">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="passWord" class="col-sm-2 control-label">密码</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" name="passWord" placeholder="root">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="boPackage" class="col-sm-2 control-label"></label>
                                    <div class="col-sm-6">
                                        <label>
                                            <input type="checkbox" name="createDal" class="flat-red" checked>
                                            生成Dao
                                        </label>
                                        <label>
                                            <input type="checkbox" name="createBo" class="flat-red">
                                            生成BO
                                        </label>
                                        <label>
                                            <input type="checkbox" name="createTransfer" class="flat-red">
                                            生成Transfer
                                        </label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="doPackage" class="col-sm-2 control-label">DO包名</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" name="doPackage"
                                               placeholder="com.xxx.xxx.dal.dataobject">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="daoPackage" class="col-sm-2 control-label">Dao包名</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" name="daoPackage"
                                               placeholder="com.xxx.xxx.dal">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="boPackage" class="col-sm-2 control-label">BO包名</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" name="boPackage"
                                               placeholder="com.xxx.xxx.entity">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="transferPackage" class="col-sm-2 control-label">Transfer包名</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" name="transferPackage"
                                               placeholder="com.xxx.xxx.transfer">
                                    </div>
                                </div>
                            </div>
                            <div class="box-footer">
                                <button type="button" class="btn btn-default" id="submitGenerateInfo">确定</button>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="box box-success">
                        <div class="box-header with-border">
                            <h3 class="box-title">结果</h3>
                        </div>
                        <form class="form-horizontal">
                            <div class="box-body">
                                <img class="img-responsive" src="/dist/img/demo.jpeg"/>
                            </div>
                            <div class="box-footer">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
    </div>
<#include "common/footer.ftl" />
</div>

<script src="/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="/bootstrap/js/bootstrap.min.js"></script>
<script src="/plugins/iCheck/icheck.min.js"></script>

<script>
    $(function () {
        $('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
            checkboxClass: 'icheckbox_flat-green',
            radioClass: 'iradio_flat-green'
        });
    });

    $("#submitGenerateInfo").click(function () {
        if ($("input[name='port']").val() == "") {
            $("input[name='port']").val($("input[name='port']").attr('placeholder'));
        }
        if ($("input[name='databaseIp']").val() == "") {
            $("input[name='databaseIp']").val($("input[name='databaseIp']").attr('placeholder'));
        }
        if ($("input[name='database']").val() == "") {
            $("input[name='database']").val($("input[name='database']").attr('placeholder'));
        }
        if ($("input[name='userName']").val() == "") {
            $("input[name='userName']").val($("input[name='userName']").attr('placeholder'));
        }
        if ($("input[name='passWord']").val() == "") {
            $("input[name='passWord']").val($("input[name='passWord']").attr('placeholder'));
        }
        if ($("input[name='doPackage']").val() == "") {
            $("input[name='doPackage']").val($("input[name='doPackage']").attr('placeholder'));
        }
        if ($("input[name='daoPackage']").val() == "") {
            $("input[name='daoPackage']").val($("input[name='daoPackage']").attr('placeholder'));
        }
        if ($("input[name='boPackage']").val() == "") {
            $("input[name='boPackage']").val($("input[name='boPackage']").attr('placeholder'));
        }
        if ($("input[name='transferPackage']").val() == "") {
            $("input[name='transferPackage']").val($("input[name='transferPackage']").attr('placeholder'));
        }
        var form = new FormData(document.getElementById("generateCode"));
        $.ajax({
            url: "${base}/generate_code",
            type: "post",
            data: form,
            processData: false,
            contentType: false,
            success: function (data) {
                if (data != "error") {
                    location.href = "${base}/download_zip?zipFilePath=" + data
                } else {
                    alert("错误！！!");
                }
            },
            error: function (e) {
                alert("错误！！!");
            }
        });
    });
</script>
</body>
</html>