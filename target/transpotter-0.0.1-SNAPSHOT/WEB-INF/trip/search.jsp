<%@ page language="java" isELIgnored="false"
	contentType="text/html;charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<div>
	<h1>Rechercher un covoit'</h1>
	<form class="form-horizontal col-sm-12">
		<div class="col-sm-6">
			<div class="form-group">
				<label for="adrDep" class="col-sm-2 control-label">Départ</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="adrDep"
						value="Jean Jaurès Toulouse" placeholder="Départ">
				</div>
			</div>
			<div class="form-group">
				<label for="mode" class="col-sm-2 control-label">Transport</label>
				<div class="col-sm-10">
					<select class="form-control" id="mode" onchange="calcRoute();">
						<option value="WALKING">marche</option>
						<option value="BICYCLING">vélo</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label for="adrArr" class="col-sm-2 control-label">Arrivée</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="adrArr" disabled
						value="22 Impasse Charles Fourier, 31200 Toulouse"
						placeholder="Arrivée">
				</div>
			</div>
			<div class="form-group">
				<label for="radius" class="col-sm-2 control-label">Radius
					(mètres)</label>
				<div class="col-sm-10">
					<input type="number" class="form-control" id="radius" value="250"
						placeholder="Radius">
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="button" onclick="rechercher('adrDep','adrArr', 'mode', 'radius');"
						class="btn btn-default">Rechercher</button>
				</div>
			</div>
		</div>
	</form>
	<br>
	<div class="row" style="width: 100%; height: 800px;">
		<div style="width: 100%; height: 100%;">
			<div class="col-sm-8" id="map_canvas"
				style="width: 70%; height: 80%;"></div>
			<div class="col-sm-4" id="divRoute" style="width: 29%; height: 80%"></div>
		</div>
	</div>
</div>
