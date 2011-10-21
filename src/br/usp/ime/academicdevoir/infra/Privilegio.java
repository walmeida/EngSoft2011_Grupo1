package br.usp.ime.academicdevoir.infra;

public enum Privilegio {
    ALUNO(0), MONITOR(1), PROFESSOR(2), ADMINISTRADOR(3);
    
    private final int privilegio;
    
    Privilegio(int p) {
    	this.privilegio = p;
    }
    
    public int privilegioToInt() {
    	return this.privilegio;
    }
    
    
}
