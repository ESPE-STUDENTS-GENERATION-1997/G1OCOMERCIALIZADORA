const modalContainerInfoUser = document.querySelector("#info-user");
const modalContainerltsPermUser = document.querySelector("#permissons-list-user");



const buildContainerInfoUser = (user = {}) => {
	return `
	<hr/>
	<div class="container">
				<div class="row">
					<div class="col-2">Nombre:</div>
					<div class="col-6">
					<input name="name"  value="${user.names}" class="form-control"/>
					</div>		
				</div>
				<div class="row">
					<div class="col-2">Apellido:</div>
					<div class="col-6">
					<input name="lastnames"  value="${user.lastnames}" class="form-control" />
					</div>	
				</div>
				<div class="row">
					<div class="col-2">Username:</div>
					<div class="col-6">
					<input name="nickname"  value="${user.nickname}"  class="form-control" />
					</div>	
				</div>
				<div class="row">
					<div class="col-2">Correo</div>
					<div class="col-6">
					<input name="email"  value="${user.email}"  class="form-control"  />
					</div>		
				</div>
			</div>
				`
}

const isChecked = (flag) => {
	if(flag){
		return "checked";
	}else{
		return "";
	}
}

const buildContainerLtsPermUser = (LtsPermUser = [{}]) => {
	let LtsItem = "";
	for(let i = 0; i < LtsPermUser.length; i++){
		let item = ` 
		<div class="form-check">
		<input	class="form-check-input" type="checkbox" id="${LtsPermUser[i].keywordSystem}"
				value="${LtsPermUser[i].codeAssignamet? LtsPermUser[i].codeAssignamet: LtsPermUser[i].keywordSystem }" name="newPermisons"
				${isChecked(LtsPermUser[i].isSelected)}  /> 
		<label class="form-check-label"> ${LtsPermUser[i].nameSystem}:  ${LtsPermUser[i].descriptionSystem}</label>
		</div>
		`;
		
		LtsItem = LtsItem + item;
	} 
	return LtsItem;
}

function getPermissonsUser(userObj) {
	if (userObj && typeof userObj == "object") {
		fetch(`/api/user/${userObj.nickname}/profiles`)
			.then(response => response.json())
			.then(data => {
				console.log(data);
				modalContainerInfoUser.innerHTML = buildContainerInfoUser(userObj);
				modalContainerltsPermUser.innerHTML = buildContainerLtsPermUser(data);
				$("#modal_editar_usuario_permisos").modal('show');
			}
			).catch(error => {
				console.error({error})
				alert("Error al consultar los permisos del usuario");});
	} else {
		alert("Error al revisar los datos del usuario seleccionado.")
	}


}



