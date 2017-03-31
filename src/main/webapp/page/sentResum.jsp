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

    <title>Gentallela Alela! | </title>
    <%@include file="/common/common_css_js.jspf" %>

</head>


<body>
<div class="container body">


    <div class="main_container">


        <!-- page content -->
        <div class="right_col" role="main">
            <div>
                <div class="clearfix"></div>

                <div class="row">

                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="x_panel">
                            <div class="x_title">
                                <h2 class="nav navbar-right">成都IT内推圈</h2>

                                <div class="clearfix"></div>
                            </div>

                            <div class="x_content" style="min-height: 850px;">
								<span class="section">
									<h1>个人信息</h1></span>

                                <form class="form-horizontal form-label-left"
                                      action="${baseUrl}/JobSeekerInfo/addJobSeekerInfoAndResume.do"
                                      method="POST" enctype="multipart/form-data">
                                    <%--投递人姓名--%>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12"
                                               for="jobSeekerName">姓名<span
                                                class="required"><font color="red">*</font></span>
                                        </label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input id="jobSeekerName" class="form-control col-md-7 col-xs-12"
                                                   data-validate-length-range="6" data-validate-words="1"
                                                   name="jobSeekerName"
                                                   placeholder="例如:张三" required="required"
                                                   type="text">
                                        </div>
                                    </div>
                                    <%--联系电话--%>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12"
                                               for="telephone">联系电话<span class="required"><font
                                                color="red">*</font></span>
                                        </label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input type="tel" id="telephone" name="jobSeekerPhone" required="required"
                                                   data-validate-length-range="8,20"
                                                   class="form-control col-md-7 col-xs-12">
                                        </div>
                                    </div>
                                    <%--请输入邮箱--%>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">邮箱 <span
                                                class="required"><font
                                                color="red">*</font></span>
                                        </label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input type="email" id="email" name="jobSeekerEmail" required="required"
                                                   class="form-control col-md-7 col-xs-12">
                                        </div>
                                    </div>

                                    <%--性别--%>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12">性别</label>
                                        <div class="col-md-6 col-sm-6 col-xs-12" style="margin-top:5px">
                                            <input type="radio" class="flat form-control col-md-7 col-xs-12"
                                                   name="jobSeekerSex" id="genderM" value="1" checked="" required/> 男
                                            <input type="radio" class="flat form-control col-md-7 col-xs-12"
                                                   name="jobSeekerSex" id="genderF" value="2"/>女
                                        </div>
                                    </div>
                                    <%--微信ID--%>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="wechat">微信ID <span
                                                class="required">*</span>
                                        </label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input type="wechat" id="wechat" name="jobSeekerWeixin" data-validate-linked="wechat"
                                                   required="required" class="form-control col-md-7 col-xs-12">
                                        </div>
                                    </div>
                                    <%--毕业院校--%>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="college">毕业院校
                                            <span class="required">*</span>
                                        </label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input type="text" id="college" name="graduateSchool" required="required"
                                            <%--data-validate-minmax="10,100"--%>
                                                   class="form-control col-md-7 col-xs-12">
                                        </div>
                                    </div>
                                    <%--专业--%>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="Major">专业 <span
                                                class="required">*</span>
                                        </label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input type="text" id="Major" name="majorSubjects" required="required"
                                                   placeholder="所学专业" class="form-control col-md-7 col-xs-12">
                                        </div>
                                    </div>
                                    <%--毕业时间--%>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12">毕业时间 <span
                                                class="required">*</span>
                                        </label>
                                        <div class="col-md-3 col-sm-3 col-xs-6" style="padding-left: 0px;">
                                            <div class="daterangepicker dropdown-menu ltr single opensright show-calendar picker_3 xdisplay">
                                                <div class="calendar left single" style="display: block;">
                                                    <div class="daterangepicker_input"><input
                                                            class="input-mini form-control active" type="text"
                                                            name="daterangepicker_start" value=""
                                                            style="display: none;"><i
                                                            class="fa fa-calendar glyphicon glyphicon-calendar"
                                                            style="display: none;"></i>
                                                        <div class="calendar-time" style="display: none;">
                                                            <div></div>
                                                            <i class="fa fa-clock-o glyphicon glyphicon-time"></i></div>
                                                    </div>
                                                    <div class="calendar-table">
                                                        <table class="table-condensed">
                                                            <thead>
                                                            <tr>
                                                                <th class="prev available"><i
                                                                        class="fa fa-chevron-left glyphicon glyphicon-chevron-left"></i>
                                                                </th>
                                                                <th colspan="5" class="month">Oct 2016</th>
                                                                <th class="next available"><i
                                                                        class="fa fa-chevron-right glyphicon glyphicon-chevron-right"></i>
                                                                </th>
                                                            </tr>
                                                            <tr>
                                                                <th>Su</th>
                                                                <th>Mo</th>
                                                                <th>Tu</th>
                                                                <th>We</th>
                                                                <th>Th</th>
                                                                <th>Fr</th>
                                                                <th>Sa</th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>
                                                            <tr>
                                                                <td class="weekend off available" data-title="r0c0">25
                                                                </td>
                                                                <td class="off available" data-title="r0c1">26</td>
                                                                <td class="off available" data-title="r0c2">27</td>
                                                                <td class="off available" data-title="r0c3">28</td>
                                                                <td class="off available" data-title="r0c4">29</td>
                                                                <td class="off available" data-title="r0c5">30</td>
                                                                <td class="weekend available" data-title="r0c6">1</td>
                                                            </tr>
                                                            <tr>
                                                                <td class="weekend available" data-title="r1c0">2</td>
                                                                <td class="available" data-title="r1c1">3</td>
                                                                <td class="available" data-title="r1c2">4</td>
                                                                <td class="available" data-title="r1c3">5</td>
                                                                <td class="available" data-title="r1c4">6</td>
                                                                <td class="available" data-title="r1c5">7</td>
                                                                <td class="weekend available" data-title="r1c6">8</td>
                                                            </tr>
                                                            <tr>
                                                                <td class="weekend available" data-title="r2c0">9</td>
                                                                <td class="available" data-title="r2c1">10</td>
                                                                <td class="available" data-title="r2c2">11</td>
                                                                <td class="available" data-title="r2c3">12</td>
                                                                <td class="available" data-title="r2c4">13</td>
                                                                <td class="available" data-title="r2c5">14</td>
                                                                <td class="weekend available" data-title="r2c6">15</td>
                                                            </tr>
                                                            <tr>
                                                                <td class="weekend available" data-title="r3c0">16</td>
                                                                <td class="available" data-title="r3c1">17</td>
                                                                <td class="today active start-date active end-date available"
                                                                    data-title="r3c2">18
                                                                </td>
                                                                <td class="available" data-title="r3c3">19</td>
                                                                <td class="available" data-title="r3c4">20</td>
                                                                <td class="available" data-title="r3c5">21</td>
                                                                <td class="weekend available" data-title="r3c6">22</td>
                                                            </tr>
                                                            <tr>
                                                                <td class="weekend available" data-title="r4c0">23</td>
                                                                <td class="available" data-title="r4c1">24</td>
                                                                <td class="available" data-title="r4c2">25</td>
                                                                <td class="available" data-title="r4c3">26</td>
                                                                <td class="available" data-title="r4c4">27</td>
                                                                <td class="available" data-title="r4c5">28</td>
                                                                <td class="weekend available" data-title="r4c6">29</td>
                                                            </tr>
                                                            <tr>
                                                                <td class="weekend available" data-title="r5c0">30</td>
                                                                <td class="available" data-title="r5c1">31</td>
                                                                <td class="off available" data-title="r5c2">1</td>
                                                                <td class="off available" data-title="r5c3">2</td>
                                                                <td class="off available" data-title="r5c4">3</td>
                                                                <td class="off available" data-title="r5c5">4</td>
                                                                <td class="weekend off available" data-title="r5c6">5
                                                                </td>
                                                            </tr>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                                <div class="calendar right" style="display: none;">
                                                    <div class="daterangepicker_input"><input
                                                            class="input-mini form-control" type="text"
                                                            name="daterangepicker_end" value=""
                                                            style="display: none;"><i
                                                            class="fa fa-calendar glyphicon glyphicon-calendar"
                                                            style="display: none;"></i>
                                                        <div class="calendar-time" style="display: none;">
                                                            <div></div>
                                                            <i class="fa fa-clock-o glyphicon glyphicon-time"></i></div>
                                                    </div>
                                                    <div class="calendar-table">
                                                        <table class="table-condensed">
                                                            <thead>
                                                            <tr>
                                                                <th></th>
                                                                <th colspan="5" class="month">Nov 2016</th>
                                                                <th class="next available"><i
                                                                        class="fa fa-chevron-right glyphicon glyphicon-chevron-right"></i>
                                                                </th>
                                                            </tr>
                                                            <tr>
                                                                <th>Su</th>
                                                                <th>Mo</th>
                                                                <th>Tu</th>
                                                                <th>We</th>
                                                                <th>Th</th>
                                                                <th>Fr</th>
                                                                <th>Sa</th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>
                                                            <tr>
                                                                <td class="weekend off available" data-title="r0c0">30
                                                                </td>
                                                                <td class="off available" data-title="r0c1">31</td>
                                                                <td class="available" data-title="r0c2">1</td>
                                                                <td class="available" data-title="r0c3">2</td>
                                                                <td class="available" data-title="r0c4">3</td>
                                                                <td class="available" data-title="r0c5">4</td>
                                                                <td class="weekend available" data-title="r0c6">5</td>
                                                            </tr>
                                                            <tr>
                                                                <td class="weekend available" data-title="r1c0">6</td>
                                                                <td class="available" data-title="r1c1">7</td>
                                                                <td class="available" data-title="r1c2">8</td>
                                                                <td class="available" data-title="r1c3">9</td>
                                                                <td class="available" data-title="r1c4">10</td>
                                                                <td class="available" data-title="r1c5">11</td>
                                                                <td class="weekend available" data-title="r1c6">12</td>
                                                            </tr>
                                                            <tr>
                                                                <td class="weekend available" data-title="r2c0">13</td>
                                                                <td class="available" data-title="r2c1">14</td>
                                                                <td class="available" data-title="r2c2">15</td>
                                                                <td class="available" data-title="r2c3">16</td>
                                                                <td class="available" data-title="r2c4">17</td>
                                                                <td class="available" data-title="r2c5">18</td>
                                                                <td class="weekend available" data-title="r2c6">19</td>
                                                            </tr>
                                                            <tr>
                                                                <td class="weekend available" data-title="r3c0">20</td>
                                                                <td class="available" data-title="r3c1">21</td>
                                                                <td class="available" data-title="r3c2">22</td>
                                                                <td class="available" data-title="r3c3">23</td>
                                                                <td class="available" data-title="r3c4">24</td>
                                                                <td class="available" data-title="r3c5">25</td>
                                                                <td class="weekend available" data-title="r3c6">26</td>
                                                            </tr>
                                                            <tr>
                                                                <td class="weekend available" data-title="r4c0">27</td>
                                                                <td class="available" data-title="r4c1">28</td>
                                                                <td class="available" data-title="r4c2">29</td>
                                                                <td class="available" data-title="r4c3">30</td>
                                                                <td class="off available" data-title="r4c4">1</td>
                                                                <td class="off available" data-title="r4c5">2</td>
                                                                <td class="weekend off available" data-title="r4c6">3
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td class="weekend off available" data-title="r5c0">4
                                                                </td>
                                                                <td class="off available" data-title="r5c1">5</td>
                                                                <td class="off available" data-title="r5c2">6</td>
                                                                <td class="off available" data-title="r5c3">7</td>
                                                                <td class="off available" data-title="r5c4">8</td>
                                                                <td class="off available" data-title="r5c5">9</td>
                                                                <td class="weekend off available" data-title="r5c6">10
                                                                </td>
                                                            </tr>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                                <div class="ranges" style="display: none;">
                                                    <div class="range_inputs">
                                                        <button class="applyBtn btn btn-sm btn-success" type="button">
                                                            Apply
                                                        </button>
                                                        <button class="cancelBtn btn btn-sm btn-default" type="button">
                                                            Cancel
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                            <fieldset>
                                                <div <%--class="control-group"--%>>
                                                    <div class="controls">
                                                        <div class="col-md-11 xdisplay_inputx form-group has-feedback">
                                                            <input type="text" class="form-control has-feedback-left" name="graduateDate"
                                                                   id="single_cal3" placeholder="毕业时间"
                                                                   aria-describedby="inputSuccess2Status3">
                                                            <span class="fa fa-calendar-o form-control-feedback left"
                                                                  aria-hidden="true"></span>
                                                            <span id="inputSuccess2Status3"
                                                                  class="sr-only">(success)</span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </fieldset>
                                        </div>
                                    </div>

                                        <%--职位编码--%>
                                        <div class="item form-group">
                                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="jobCode">职位编码 <span
                                                    class="required">*</span>
                                            </label>
                                            <div class="col-md-6 col-sm-6 col-xs-12">
                                                <input type="text" id="jobCode" name="jobCode" required="required"
                                                       placeholder="职位编码" class="form-control col-md-7 col-xs-12">
                                            </div>
                                        </div>
                                    <%--上传附件--%>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12"
                                               for="fileName">简历上传<span class="required">*</span>
                                        </label>
                                        <div class="col-md-4 col-sm-4 col-xs-8 " style="padding-left: 0px;">
                                            <%--<input id="fileUpload" required="required" name="file" class="form-control col-md-7 col-xs-12"></input>--%>
                                            <input id="fileName" name="fileName" type="file"
                                                   class="file orm-control col-md-7 col-xs-12"></input>
                                        </div>
                                    </div>
                                    <div class="ln_solid"></div>
                                    <div class="form-group">
                                        <div class="col-md-6 col-md-offset-3 pull-right">

                                            <button class="btn btn-primary">取消</button>
                                            <button id="submit" type="submit" class="btn btn-success">提交</button>
                                        </div>
                                    </div>
                                </form>

                                <%--  <form class="form-horizontal form-label-left">
                                      &lt;%&ndash;用户名&ndash;%&gt;
                                      <div class="item form-group">
                                          <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">用户名<span
                                                  class="required"><font color="red"> *</font></span>
                                          </label>
                                          <div class="col-md-6 col-sm-6 col-xs-12">
                                              <input id="name" class="form-control col-md-7 col-xs-12"
                                                     data-validate-length-range="6"  name="name"
                                                     placeholder="用户姓名" required="required"
                                                     type="text">
                                          </div>
                                      </div>
                                      <div class="item form-group">
                                          <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">Email <span
                                                  class="required">*</span>
                                          </label>
                                          <div class="col-md-6 col-sm-6 col-xs-12">
                                              <input type="email" id="email" name="email" required="required"
                                                     class="form-control col-md-7 col-xs-12">
                                          </div>
                                      </div>
                                      <div class="item form-group">
                                          <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">Confirm
                                              Email <span class="required">*</span>
                                          </label>
                                          <div class="col-md-6 col-sm-6 col-xs-12">
                                              <input type="email" id="email2" name="confirm_email"
                                                     data-validate-linked="email" required="required"
                                                     class="form-control col-md-7 col-xs-12">
                                          </div>
                                      </div>
                                      <div class="item form-group">
                                          <label class="control-label col-md-3 col-sm-3 col-xs-12" for="number">Number
                                              <span class="required">*</span>
                                          </label>
                                          <div class="col-md-6 col-sm-6 col-xs-12">
                                              <input type="number" id="number" name="number" required="required"
                                                     data-validate-minmax="10,100"
                                                     class="form-control col-md-7 col-xs-12">
                                          </div>
                                      </div>
                                      <div class="item form-group">
                                          <label class="control-label col-md-3 col-sm-3 col-xs-12" for="website">Website
                                              URL <span class="required">*</span>
                                          </label>
                                          <div class="col-md-6 col-sm-6 col-xs-12">
                                              <input type="url" id="website" name="website" required="required"
                                                     placeholder="www.website.com"
                                                     class="form-control col-md-7 col-xs-12">
                                          </div>
                                      </div>
                                      <div class="item form-group">
                                          <label class="control-label col-md-3 col-sm-3 col-xs-12" for="occupation">Occupation
                                              <span class="required">*</span>
                                          </label>
                                          <div class="col-md-6 col-sm-6 col-xs-12">
                                              <input id="occupation" type="text" name="occupation"
                                                     data-validate-length-range="5,20"
                                                     class="optional form-control col-md-7 col-xs-12">
                                          </div>
                                      </div>
                                      <div class="item form-group">
                                          <label for="password" class="control-label col-md-3">Password</label>
                                          <div class="col-md-6 col-sm-6 col-xs-12">
                                              <input id="password" type="password" name="password"
                                                     data-validate-length="6,8" class="form-control col-md-7 col-xs-12"
                                                     required="required">
                                          </div>
                                      </div>
                                      <div class="item form-group">
                                          <label for="password2" class="control-label col-md-3 col-sm-3 col-xs-12">Repeat
                                              Password</label>
                                          <div class="col-md-6 col-sm-6 col-xs-12">
                                              <input id="password2" type="password" name="password2"
                                                     data-validate-linked="password"
                                                     class="form-control col-md-7 col-xs-12" required="required">
                                          </div>
                                      </div>
                                      <div class="item form-group">
                                          <label class="control-label col-md-3 col-sm-3 col-xs-12" for="telephone">Telephone
                                              <span class="required">*</span>
                                          </label>
                                          <div class="col-md-6 col-sm-6 col-xs-12">
                                              <input type="tel" id="telephone" name="phone" required="required"
                                                     data-validate-length-range="8,20"
                                                     class="form-control col-md-7 col-xs-12">
                                          </div>
                                      </div>
                                      <div class="item form-group">
                                          <label class="control-label col-md-3 col-sm-3 col-xs-12" for="textarea">Textarea
                                              <span class="required">*</span>
                                          </label>
                                          <div class="col-md-6 col-sm-6 col-xs-12">
                                              <textarea id="textarea" required="required" name="textarea"
                                                        class="form-control col-md-7 col-xs-12"></textarea>
                                          </div>
                                      </div>
                                      <div class="ln_solid"></div>
                                      <div class="form-group">
                                          <div class="col-md-6 col-md-offset-3">
                                              <button type="submit" class="btn btn-primary">Cancel</button>
                                              <button id="send" type="submit" class="btn btn-success">Submit</button>
                                          </div>
                                      </div>
                                  </form>--%>

                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- footer content -->
            <footer>
                <div class="copyright-info">
                    <p class="pull-right">成都IT内推圈</a>
                    </p>
                </div>
                <div class="clearfix"></div>
            </footer>
            <!-- /footer content -->

        </div>
        <!-- /page content -->
    </div>

</div>
</body>
<!-- bootstrap progress js -->
<script src="${baseUrl}/js/gentelella/production/js/progressbar/bootstrap-progressbar.min.js"></script>
<!-- icheck -->
<script src="${baseUrl}/js/gentelella/production/js/icheck/icheck.min.js"></script>
<!-- pace -->
<script src="${baseUrl}/js/gentelella/production/js/pace/pace.min.js"></script>
<script src="${baseUrl}/js/gentelella/production/js/custom.js"></script>
<!-- form validation -->
<script src="${baseUrl}/js/gentelella/production/js/validator/validator.js"></script>
<%--验证的js--%>
<%--<script>
    // initialize the validator function
    validator.message['date'] = 'not a real date';

    // validate a field on "blur" event, a 'select' on 'change' event & a '.reuired' classed multifield on 'keyup':
    $('form')
        .on('blur', 'input[required], input.optional, select.required', validator.checkField)
        .on('change', 'select.required', validator.checkField)
        .on('keypress', 'input[required][pattern]', validator.keypress);

    $('.multi.required')
        .on('keyup blur', 'input', function () {
            validator.checkField.apply($(this).siblings().last()[0]);
        });

    // bind the validation to the form submit event
    //$('#send').click('submit');//.prop('disabled', true);

    $('form').submit(function (e) {
        e.preventDefault();
        var submit = true;
        // evaluate the form using generic validaing
        if (!validator.checkAll($(this))) {
            submit = false;
        }

        if (submit)
            this.submit();
        return false;
    });

    /* FOR DEMO ONLY */
    $('#vfields').change(function () {
        $('form').toggleClass('mode2');
    }).prop('checked', false);

    $('#alerts').change(function () {
        validator.defaults.alerts = (this.checked) ? false : true;
        if (this.checked)
            $('form .alert').remove();
    }).prop('checked', false);
</script>--%>
<!-- daterangepicker -->
<script type="text/javascript" src="${baseUrl}/js/gentelella/production/js/moment/moment.min.js"></script>
<script type="text/javascript" src="${baseUrl}/js/gentelella/production/js/datepicker/daterangepicker.js"></script>
<%--<script>
	//验证函数的初始化
    // initialize the validator function
    validator.message['date'] = 'not a real date';

    // validate a field on "blur" event, a 'select' on 'change' event & a '.reuired' classed multifield on 'keyup':
    $('form')
        .on('blur', 'input[required], input.optional, select.required', validator.checkField)
        .on('change', 'select.required', validator.checkField)
        .on('keypress', 'input[required][pattern]', validator.keypress);

    $('.multi.required')
        .on('keyup blur', 'input', function() {
            validator.checkField.apply($(this).siblings().last()[0]);
        });

    // bind the validation to the form submit event
    //$('#send').click('submit');//.prop('disabled', true);

    $('form').submit(function(e) {
        e.preventDefault();
        var submit = true;
        // evaluate the form using generic validaing
        if (!validator.checkAll($(this))) {
            submit = false;
        }

        if (submit)
            this.submit();
        return false;
    });

	/* FOR DEMO ONLY */
    $('#vfields').change(function() {
        $('form').toggleClass('mode2');
    }).prop('checked', false);

    $('#alerts').change(function() {
        validator.defaults.alerts = (this.checked) ? false : true;
        if (this.checked)
            $('form .alert').remove();
    }).prop('checked', false);
</script>--%>


<!-- datepicker -->
<script type="text/javascript">
    $(document).ready(function () {

        var cb = function (start, end, label) {
            console.log(start.toISOString(), end.toISOString(), label);
            $('#reportrange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
            //alert("Callback has fired: [" + start.format('MMMM D, YYYY') + " to " + end.format('MMMM D, YYYY') + ", label = " + label + "]");
        }

        var optionSet1 = {
            startDate: moment().subtract(29, 'days'),
            endDate: moment(),
            minDate: '01/01/2012',
            maxDate: '12/31/2015',
            dateLimit: {
                days: 60
            },
            showDropdowns: true,
            showWeekNumbers: true,
            timePicker: false,
            timePickerIncrement: 1,
            timePicker12Hour: true,
            ranges: {
                'Today': [moment(), moment()],
                'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
                'Last 7 Days': [moment().subtract(6, 'days'), moment()],
                'Last 30 Days': [moment().subtract(29, 'days'), moment()],
                'This Month': [moment().startOf('month'), moment().endOf('month')],
                'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
            },
            opens: 'left',
            buttonClasses: ['btn btn-default'],
            applyClass: 'btn-small btn-primary',
            cancelClass: 'btn-small',
            format: 'MM/DD/YYYY',
            separator: ' to ',
            locale: {
                applyLabel: 'Submit',
                cancelLabel: 'Clear',
                fromLabel: 'From',
                toLabel: 'To',
                customRangeLabel: 'Custom',
                daysOfWeek: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa'],
                monthNames: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
                firstDay: 1
            }
        };
        $('#reportrange span').html(moment().subtract(29, 'days').format('MMMM D, YYYY') + ' - ' + moment().format('MMMM D, YYYY'));
        $('#reportrange').daterangepicker(optionSet1, cb);
        $('#reportrange').on('show.daterangepicker', function () {
            console.log("show event fired");
        });
        $('#reportrange').on('hide.daterangepicker', function () {
            console.log("hide event fired");
        });
        $('#reportrange').on('apply.daterangepicker', function (ev, picker) {
            console.log("apply event fired, start/end dates are " + picker.startDate.format('MMMM D, YYYY') + " to " + picker.endDate.format('MMMM D, YYYY'));
        });
        $('#reportrange').on('cancel.daterangepicker', function (ev, picker) {
            console.log("cancel event fired");
        });
        $('#options1').click(function () {
            $('#reportrange').data('daterangepicker').setOptions(optionSet1, cb);
        });
        $('#options2').click(function () {
            $('#reportrange').data('daterangepicker').setOptions(optionSet2, cb);
        });
        $('#destroy').click(function () {
            $('#reportrange').data('daterangepicker').remove();
        });
    });
</script>
<!-- /datepicker -->
<script type="text/javascript">
    $(document).ready(function () {
        $('#single_cal1').daterangepicker({
            singleDatePicker: true,
            calender_style: "picker_1"
        }, function (start, end, label) {
            console.log(start.toISOString(), end.toISOString(), label);
        });
        $('#single_cal2').daterangepicker({
            singleDatePicker: true,
            calender_style: "picker_2"
        }, function (start, end, label) {
            console.log(start.toISOString(), end.toISOString(), label);
        });
        $('#single_cal3').daterangepicker({
            singleDatePicker: true,
            calender_style: "picker_3"
        }, function (start, end, label) {
            console.log(start.toISOString(), end.toISOString(), label);
        });
        $('#single_cal4').daterangepicker({
            singleDatePicker: true,
            calender_style: "picker_4"
        }, function (start, end, label) {
            console.log(start.toISOString(), end.toISOString(), label);
        });
    });
</script>
<script type="text/javascript">
    $(document).ready(function () {
        $('#reservation').daterangepicker(null, function (start, end, label) {
            console.log(start.toISOString(), end.toISOString(), label);
        });
    });
</script>
<!-- /datepicker -->

</html>
