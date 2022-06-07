//Reglage de la luminosité
function Nuit(){
document.body.style.backgroundColor="#202020";
document.body.style.color="white";
}
function Jour(){
document.body.style.backgroundColor="white";
document.body.style.color="black";
}

$(document).ready(function() {

//Partie Affichage des résultats et mise à jour des select
	let $listeEtudiant = $("#listEtudiant");
	let $listeSpecialite = $("#listSpecialite");
	let $selectSpecialite = $("#selectSpecialite");

    //Remplie les select avec les spécialités existantes
    $.get("http://localhost:8080/api/specialite",function(resp){
    	resp.forEach( s => {
    		appendToListSpecialite(s);
    	});
    });
    //Affiche les etudiants à la suite des GET
	function AfficheEtudiant(etudiants) {
        	etudiants.forEach(etudiant => {$listeEtudiant.append(`<li id="etudiant-${etudiant.IdEtudiant}" class="list-group-item">${etudiant.IdEtudiant} - ${etudiant.NomEtudiant} - ${etudiant.PrenomEtudiant} - ${etudiant.Age}</li>`)});
        }
	//Affiche les specialites à la suite des GET
    function AfficheSpecialite(specialites) {
    	specialites.forEach(specialite => {$listeSpecialite.append(`<li id="specialite-${specialite.IdSpecialite}" class="list-group-item">${specialite.IdSpecialite} - ${specialite.NomSpecialite}</li>`)});
    }

	/* Ajoute un élément li dans le select des specialites*/
	function appendToSelects(specialite) {
		$selectSpecialite.append(`<option value="${specialite.IdSpecialite}">${specialite.NomSpecialite}</option>`)
	}
//Fin de la Partie Affichage des résultats et mise à jour des select

//Partie requete de la page Etudiant

    //Creation d'un etudiant
    $('#ajouteEtudiant').click(function(){
        let IdEtudiant = $('#ajouteIdEtudiant').val();
        let NomEtudiant = $('#ajouteNomEtudiant').val();
		let PrenomEtudiant = $('#ajoutePrenomEtudiant').val();
		let Age = $('#Age').val();
		let IdSpecialite = $selectSpecialite.val();
		$.ajax({
		    type: "POST",
		    url: "http://localhost:8080/api/etudiant",
		    data: JSON.stringify({
                    "IdEtudiant": IdEtudiant, "NomEtudiant": NomEtudiant,"PrenomEtudiant": PrenomEtudiant
            	}),
		    contentType: "application/json; charset=utf-8",
		    dataType: "json",
		    success: function(data){
		        appendToListEtudiant(data);
		        ajouteEtudiantToSpecialite(data.IdEtudiant, IdSpecialite);
		    }
		});
		return false;
	});
	function ajouteEtudiantToSpecialite(IdEtudiant, IdSpecialite){
	    $.ajax({
        		type: "PATCH",
        		url: "http://localhost:8080/api/specialite/"+IdSpecialite+"/"+IdEtudiant,
        		//Les données envoyés se trouvent dans l'url mais je les envoies aussi en JSON
        		data: JSON.stringify({"IdSpecialite" : IdSpecialite, "IdEtudiant" : IdEtudiant}),
        		contentType: "application/json; charset=utf-8",
        		dataType: "json",
        	});
	}

	//Suppression d'un etudiant
	$('#supprimeEtudiant').click(function(){
        let IdEtudiant = $('#supprimeIdEtudiant').val();
    	$.ajax({
    	    type: "DELETE",
    	    url: "http://localhost:8080/api/etudiant/"+IdEtudiant,
    	    success: function(data){
    	        supprimeEtudiantToSpecialite(data.IdEtudiant);
    	    }
    	});
    	return false;
    });

    //Affichage de tous les etudiants
        $('#findAllEtudiant').click(function(){
        	$.ajax({
        	    type: "GET",
        	    url: "http://localhost:8080/api/etudiant",
        	    success: function(data){ AfficheEtudiant(data);}
        	});
        	return false;
        });
    //Recherhce d'etudiant par id
        $('#findEtudiantbyIdEtudiant').click(function(){
            let IdEtudiant = $('#rechercheIdEtudiant').val();
        	$.ajax({
        	    type: "GET",
        	    url: "http://localhost:8080/api/etudiant/"+IdEtudiant,
        	    success: function(data){ AfficheEtudiant(data);}
        	});
        	return false;
        });
    //Recherhce d'etudiant par nom et prenom
        $('#findEtudiantbyNomEtudiantAndPrenomEtudiant').click(function(){
            let NomEtudiant = $('#rechercheNomEtudiant').val();
        	let PrenomEtudiant = $('#recherchePrenomEtudiant').val();
        	$.ajax({
        	    type: "GET",
        	    url: "http://localhost:8080/api/etudiant?nom="+NomEtudiant+"&prenom="+PrenomEtudiant,
        	    success: function(data){ AfficheEtudiant(data);}
        	});
        	return false;
        });
//Fin de la Partie requete de la page Etudiant

//Partie requete de la page Specialite
    //Creation d'une specialite
    $('#ajouteSpecialite').click(function(){
        let IdSpecialite = $('#ajouteIdSpecialite').val();
        let NomSpecialite = $('#ajouteNomSpecialite').val();
		$.ajax({
		    type: "POST",
		    url: "http://localhost:8080/api/specialite",
		    data: JSON.stringify({
                    "IdSpecialite": IdSpecialite, "NomSpecialite": NomSpecialite
            	}),
		    contentType: "application/json; charset=utf-8",
		    dataType: "json",
		    success:function(data){ appendToSelects(data);}
		});
		return false;
	});

	//Affichage de toutes les specialites
        $('#findAllSpecialite').click(function(){
            $.ajax({
                type: "GET",
                url: "http://localhost:8080/api/specialite",
                success: function(data){ AfficheSpecialite(data);}
            });
            return false;
        });

	//Suppression d'une specialite
	$('#supprimeSpecialite').click(function(){
        let IdSpecialite = $('#supprimeIdSpecialite').val();
    	$.ajax({
    	    type: "DELETE",
    	    url: "http://localhost:8080/api/specialite/"+IdSpecialite,
    	    success: $.get("http://localhost:8080/api/specialite",function(resp){resp.forEach( s => {appendToListSpecialite(s);});})
    	});
    	return false;
    });

    //Affiche les etudiants par specialite
        $('#findEtudiantBySpecialite').click(function(){
            let IdSpecialite = $selectSpecialite.val();
        	$.ajax({
        	    type: "GET",
        	    url: "http://localhost:8080/api/etudiant/"+IdSpecialite,
        	    success: function(data){ AfficheEtudiant(data);}
        	});
        	return false;
        });

//Fin de la Partie requete de la page Specialite

});