
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<%@ include file = "include/header.jsp" %>
<div class="container bg-white pt-4">
    <div class="row">
        <div class="col text-center">
            <h2>Kunder</h2>
            </br>
        </div>
    </div>
    <div class="row">
        <div class="col-1 text-center">
        </div>
        <div class="col-10">
            <table class="table">
                <thead class="thead-light">
                <tr>
                    <th scope="col">Kunde ID</th>
                    <th scope="col">Kunde</th>
                    <th scope="col">Saldo</th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <th scope="row">1</th>
                    <td>Mark@email.com</td>
                    <td>50</td>
                    <td><a class="btn btn-success" href="#">Ordrer</a></td>
                </tr>
                <tr>
                    <th scope="row">2</th>
                    <td>Jacob@yahoo.dk</td>
                    <td>0</td>
                    <td><a class="btn btn-success" href="#">Ordrer</a></td>
                </tr>
                <tr>
                    <th scope="row">3</th>
                    <td>Larry@gmail.com</td>
                    <td>250</td>
                    <td><a class="btn btn-success" href="#">Ordrer</a></td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="col-1 text-center">
        </div>
    </div>
    </br>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>