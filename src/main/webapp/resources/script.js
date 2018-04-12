var rad = document.myForm.roles;
var role = document.getElementById('role');
var html = '';
var classes = document.getElementById("classes");
for (var i = 0; i < rad.length; i++) {
	rad[i].onclick = function() {
		classes.innerHTML = '';
		if (this.value == 'student_id') {
			html = '';
			html += '<td><label for="studentClazz"><spring:message code="admin.select.clazz" /></label></td>';
			html += '<td><select name="studentClazz">';
			html += '<option value="0">--select class--</option>';
			html += '<c:forEach items="${clazzes}" var="clazz">';
			html += '<option value=${clazz.id }>${clazz.name}</option>';
			html += '</c:forEach>';
			html += '</select></td>';
			role.innerHTML = html;
		} else if (this.value == 'teacher_id') {
			html = '';
			html += '<td><label for="subjects"><spring:message code="admin.select.subject" /></label></td>';
			html += '<td><select id="subjects" name="subjects">';
			html += '<option value=0>--select subject--</option>';
			html += '<c:forEach items="${subjects}" var="subject">';
			html += '<option value=${subject.id}>${subject.name}</option>';
			html += '</c:forEach>';
			html += '</select></td>';
			role.innerHTML = html;

		}

	};
};

$(document)
		.ready(
				function() {
					$('#role')
							.on(
									'change',
									function() {
										var id = document
												.getElementById("subjects").value;
										$
												.ajax({
													type : 'GET',
													url : 'http://localhost:8080/eschool/getClazzes/'
															+ id,
													dataType : 'json',
													success : function(data) {
														var classes = document
																.getElementById("classes");
														var html = '';
														html += '<td><label for="teacherClazzes"><spring:message code="admin.select.clazzes"/> </label></td>';
														html += '<td><select name="teacherClazzes" multiple>';
														for (var i = 0; i < data.length; i++) {
															html += '<option value='
																	+ data[i].id
																	+ '>'
																	+ data[i].name
																	+ '</option>';
														}
														html += '</select></td>';
														classes.innerHTML = html;
													}
												});
									});

				});