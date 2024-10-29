grammar Nomina;

// Regla principal
calculoNomina : empleados calculoIndividual* calculoGrupo* listarEmpleados? guardar? EOF ;

// Registro de empleados
empleados : 'empleado' ID nombre salario (',' 'empleado' ID nombre salario)* ;

// Calculo de nómina individual
calculoIndividual : 'calcularIndividual' '(' ID ',' DIAS ')' ;

// Calculo de nómina grupal
calculoGrupo : 'calcularGrupo' '(' DIAS ')' ;

// Listar empleados registrados
listarEmpleados : 'listarEmpleados' ;

// Guardar los datos
guardar : 'guardar' '(' FILETYPE ')' ;

// Nombre del empleado
nombre : 'nombre' ':' STRING ;

// Salario del empleado
salario : 'salario' ':' NUMERO ;

// Definir tokens básicos
ID : [a-zA-Z_][a-zA-Z0-9_]* ;
DIAS : [0-9]+ ;
FILETYPE : 'json' | 'txt' ;
STRING : '"' .*? '"' ;
NUMERO : [0-9]+ ('.' [0-9]+)? ;

// Ignorar espacios y saltos de línea
WS : [ \t\r\n]+ -> skip ;
