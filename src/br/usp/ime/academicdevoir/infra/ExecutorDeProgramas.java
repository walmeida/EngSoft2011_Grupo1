package br.usp.ime.academicdevoir.infra;
import java.io.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeoutException;

/**
 * Entidade responsável pela execução de programas.
 * 
 * O código foi adaptado do projeto http://dl.dropbox.com/u/10977140/Aulas.rar
 * 
 */

public class ExecutorDeProgramas {
	
    private long timeout = Long.MAX_VALUE;
	private String stdOut = "";
	private String stdErr = "";
	
	public String getStdOut() {
		return stdOut;
	}

	public String getStdErr() {
		return stdErr;
	}

	/**
	 * Construdor padrão - Timeout vale Long.MAX_VALUE
	 */
	public ExecutorDeProgramas() {
		// Do nothing
	}

	/**
	 * Construtor
	 * 
	 * @param timeout
	 *            Tempo máximo de execução
	 */
	public ExecutorDeProgramas(long timeout) {
		this.timeout = timeout;
	}

	/**
	 * Executa programa gerado por código Java
	 * @param diretorio
	 * @param nomeDaClasse
	 * @return mensagem informando se houve algum problema durante a execução.
	 * @throws Exception
	 */
	public static String executaProgramaJava (String diretorio, 
	        String nomeDaClasse) throws Exception {
	    ExecutorDeProgramas r = new ExecutorDeProgramas(3000);
        String resp = "";
        
        // Linux
        if (diretorio.startsWith("/")) 
            resp = r.executa("java -cp " + diretorio + 
                             " -Djava.security.manager " + nomeDaClasse, null);
        // Windows
        else  
            resp = r.executa("java -cp \"" + diretorio + 
                             ";\" -Djava.security.manager " + nomeDaClasse, 
                             null);
        return resp;
	}
	
	/**
	 * Executa um processo
	 * 
	 * @param comando String com o comando a ser executado
	 * @param env variáveis do ambiente
	 * @return saida do processo
	 * @throws IOException
	 * @throws TimeoutException processo excedeu timeout
	 */
	public String executa(String comando, String[] env) throws Exception {
		Process p;
		
		System.setProperty("file.encoding", "cp850"); 

		if (env == null) {
			p = Runtime.getRuntime().exec(comando, env);
		} else {
			p = Runtime.getRuntime().exec(comando);
		}
		StreamGobbler stdin = new StreamGobbler ("stdin", p.getInputStream());
		StreamGobbler stderr = new StreamGobbler ("stderr", p.getErrorStream());
		stdin.start();
		stderr.start();

		// Estabelece um "timer" caso o programa não tenha terminado de executar
		// após o tempo estabelecido pelo timeout
		Timer timer = new Timer();
		timer.schedule(new InterrompeEscalonador(Thread.currentThread()), this.timeout);

		try {
			p.waitFor();
		} catch (InterruptedException e) {
			p.destroy();
			Runtime.getRuntime().runFinalization();
			stdin.interrupt();
			stderr.interrupt();
			return "O programa excedeu o tempo limite de execucao (" + 
			       this.timeout + " milisegundos)";
		} finally {
		
			timer.cancel();
		}

		stdOut = stdin.getText();
		stdErr = stderr.getText();
		
		if (!stdin.getText().equals("")) {
			return stdin.getText();
		} else {
			return stderr.getText();
		}

	}

	private class InterrompeEscalonador extends TimerTask {
		Thread target = null;

		public InterrompeEscalonador(Thread target) {
			this.target = target;
		}

		@Override
		public void run() {
			target.interrupt();
		}

	}
	
	private class StreamGobbler implements Runnable {
	    String name;
	    InputStream is;
	    Thread thread;
	    StringBuilder buffer = new StringBuilder();

	    public StreamGobbler(String name, InputStream is) {
	        this.name = name;
	        this.is = is;
	    }

	    public void start() {
	        thread = new Thread(this);
	        thread.start();
	    }
	    
	    public void interrupt() {
	        thread.interrupt();
	    }

	    public void run() {
	        try {
	            InputStreamReader isr = new InputStreamReader(is);
	            BufferedReader br = new BufferedReader(isr);

	            while (true) {
	                String s = br.readLine();
	                if (s == null)
	                    break;
	                //System.out.println("[" + name + "] " + s);
	                buffer.append(s + "\n");
	            }

	            is.close();

	        } catch (Exception ex) {
	            System.out.println("Problem reading stream " + name + "... :" + ex);
	            ex.printStackTrace();
	        }
	    }

	    public String getText() {
	        return this.buffer.toString();
	    }
	}

}

