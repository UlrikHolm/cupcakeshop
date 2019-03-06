
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<%@ include file = "include/header.jsp" %>
<div class="container bg-white pt-4">
    <div class="row">
        <div class="col text-center">
            <h2>Vælg og bestil her:</h2>
            </br>
        </div>
    </div>
    <form action="/FrontController" method="post">
    <div class="row">
        <div class="col-md-3 text-center">
            <label>Bund:</label>
            <select class="form-control" id="selBund">
                <option value="" disabled selected>Vælg bund
                <option>Chocolate</option>
                <option>Vanilla</option>
                <option>Nutmeg</option>
                <option>Almond</option>
            </select>
        </div>
        <div class="col-md-3 text-center">
            <label>Topping:</label>
            <select class="form-control" id="selTopping">
                <option value="" disabled selected>Vælg topping
                <option value="1">Strawberry</option>
                <option>Crispy</option>
                <option>Rum/Raisin</option>
                <option>Rasberry</option>
            </select>
        </div>
        <div class="col-md-3 text-center">
            <label>Antal:</label>
            <select class="form-control" id="selAntal">
                <option value="" disabled selected>Vælg antal
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
            </select>
        </div>
        <div class="col-md-3 text-center">
            <label>&zwnj;</label>
            <button type="button" class="btn btn-success btn-block">Læg i kurv</button>
        </div>
    </div>
    </form>
    <div class="row pt-4">
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>