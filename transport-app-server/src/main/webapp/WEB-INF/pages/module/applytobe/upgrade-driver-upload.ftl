<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width,minimum-scale=1,user-scalable=no,maximum-scale=1,initial-scale=1">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="format-detection" content="telephone=no">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title>司机升级说明</title>

	<link rel="stylesheet" href="${staticUrl}/css/apply_lanrenzhijia.css"/>
	<script src="${staticUrl}/script/module/applytobe/plugins/jquery-2.2.0.min.js"></script>
	<script src="${staticUrl}/script/module/applytobe/plugins/flexible.js"></script>
    <script src="${staticUrl}/script/module/applytobe/plugins/flexible_cj.js"></script>
	<link rel="stylesheet" href="${staticUrl}/css/apply_common.css"/>
	<link rel="stylesheet" href="${staticUrl}/css/apply_style.css"/>
	<link rel="stylesheet" href="${staticUrl}/css/apply_index.css"/>
	<link rel="stylesheet" href="${staticUrl}/css/apply_upgrade.css"/>
    <link rel="stylesheet" href="${staticUrl}/css/apply_mstation.css"/>
</head>
<body style="-webkit-overflow-scrolling: touch;" class="single">
	<section class="wrapper">
		<div class="top">
			<span class="tp-goback"></span>
			<span class="tp-title">司机升级说明</span>
		</div>
		<form action="/mobileStation/apply/carOwnerOne" id="driver_upload">
		<div class="content">
			<div class="to-driver">
				<p>道路运输许可证</p>
				<div class="driver-img">
					<img src="/images/mobileApply/images/driver-img.png" id="transportAgree">
				</div>
				<div class="upfile" style="display:none">
					<input type="hidden" id="transportAgreeFileId" name="transportAgreeFileId">
                    <input type="hidden" id="transportAgreeUrl" name="transportAgreeUrl">
				</div>
				<div id="transportAgree_status" class="driver-status">
				</div>
				<div class="driver-num">
					<input type="text" placeholder="驾驶员海关备案代码"  name="driverCustomsCode" id="driverCustomsCode">
				</div>
				<div class="btn-submit btn-driver-submit">下一步
			</div>
			</div>
		</div>
        </form>
	</section>
	<script type="text/javascript"  src="${staticUrl}/script/module/applytobe/plugins/lanrenzhijia.js"></script>
	<script type="text/javascript"  src="${staticUrl}/script/module/applytobe/plugins/common.js"></script>
	<script type="text/javascript"  src="${staticUrl}/script/module/applytobe/plugins/utils.js"></script>
	<script type="text/javascript"  src="${staticUrl}/script/module/applytobe/app/upgrade.js"></script>
</body>
</html>