<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<!-- Meta, title, CSS, favicons, etc. -->
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<title>简历投递</title>
	<%@include file="/common/common_css_js.jspf" %>
</head>
<body>
	<div class="blog-content">
		<div class="container">
        <div class="wrapper">
			<div class="contact">
	       		<h3>基本信息</h3>
				<div class="section group">				
					<div class="col span_1_of_4">
						<div class="contact-form">
							<form action="../user/uploadFile.do" method="POST" enctype="multipart/form-data">
								<div>
									<span><label>姓名</label></span>
									<span><input id="name" name="name" type="text" class="textbox" ></span>
								</div>
								<div>
									<span><label>联系电话</label></span>
									<span><input id="contactPhone" name="contactPhone" type="text" class="textbox"></span>
								</div>
								<div>
									<span><label>邮箱</label></span>
									<span><input id="email" name="email" type="text" class="form-control"></span>
								</div>
								<div>
									<span><label>微信ID</label></span>
									<span><input  id ="weixinId" name = "weixinId" type="text" class="textbox"></span>
								</div>
								<div>
									<span><label>毕业院校</label></span>
									<span><input id="school" name ="school" type="text" class="textbox"></span>
								</div>
								<div>
									<span><label>大学专业</label></span>
									<span><input id="major" name = "major" type="text" class="textbox"></span>
								</div>
								<div>
									<span><label>毕业时间</label></span>
									<span><input id = "graduateDate" name="graduateDate"  type="date" class="textbox" style="width: 98%; border-radius: 7px;border: none;"></span>
								</div>
								<div>
									<span><label class="control-lable">上传简历</label></span>
									<span><input id="fileName"  name="fileName" type="file" class="file"></span>
								</div>
								<div align="center">
									<button type="submit" class="btn btn-large btn-block">提交信息</button>
								</div>
								
							</form>
						</div>
					</div>		
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
		</div>
	</div>
</body>
</html>