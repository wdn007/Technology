// 关闭当前活动的标签
function closeCurTab(){
	document.getElementsByClassName('content-tab active').item(0).children[0].click();
}

// 添加新标签页
function addNewTab(h, label){
	var isHas = false;
	if (h == "" || $.trim(h).length == 0) {
		return false;
	}
	
	var fullWidth = $(window).width();
	if(fullWidth >= 750){
		$(".layout-side").show();
	}else{
		$(".layout-side").hide();
	}
	
	$(".content-tab").each(function() {
		if ($(this).data("id") == h) {
			if (!$(this).hasClass("active")) {
				$(this).addClass("active").siblings(".content-tab").removeClass("active");
				addTab(this);
			}
			isHas = true;
		}
	});
	if(isHas){
		$(".body-iframe").each(function() {
			if ($(this).data("id") == h) {
				$(this).show().siblings(".body-iframe").hide();
			}
		});
	}
	if (!isHas) {
		var tab = "<a href='javascript:;' class='content-tab active' data-id='"+h+"'>"+ label +" <i class='icon-font'>&#xe617;</i></a>";
		$(".content-tab").removeClass("active");
		$(".tab-nav-content").append(tab);
		var iframe = "<iframe class='body-iframe' name='iframe' width='100%' height='99%' src='"+ h +"' frameborder='0' data-id='"+ h +"' seamless></iframe>";
		$(".layout-main-body").find("iframe.body-iframe").hide().parents(".layout-main-body").append(iframe);
		addTab($(".content-tab.active"));
	}
	return false;
}