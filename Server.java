import com.zeroc.Ice.*;
 
public class Server {
 
    // ── Implementação do Printer ─────────────────────────────────────────────
    static class PrinterI implements Demo.Printer {
 
        @Override
        public String printString(String s, Current current) {
            System.out.println("[Printer] " + s);
            return "Printed: " + s;
        }
 
        @Override
        public String printUpperCase(String s, Current current) {
            String result = s.toUpperCase();
            System.out.println("[Printer] " + result);
            return "UpperCase: " + result;
        }
 
        @Override
        public String printCount(String s, Current current) {
            String result = "\"" + s + "\" tem " + s.length() + " caracteres";
            System.out.println("[Printer] " + result);
            return result;
        }
    }
 
    // ── Implementação do Scanner ─────────────────────────────────────────────
    static class ScannerI implements Demo.Scanner {
 
        private String status = "pronto";
 
        @Override
        public String scanDocument(String filename, Current current) {
            System.out.println("[Scanner] Escaneando: " + filename);
            status = "ultimo escaneamento: " + filename;
            return "Conteudo simulado de '" + filename + "': Lorem ipsum...";
        }
 
        @Override
        public String getStatus(Current current) {
            System.out.println("[Scanner] Status: " + status);
            return status;
        }
 
        @Override
        public void resetScanner(Current current) {
            System.out.println("[Scanner] Reiniciado.");
            status = "pronto";
        }
    }
 
    // ── Main ─────────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        try (Communicator communicator = Util.initialize(args)) {
 
            ObjectAdapter adapter = communicator.createObjectAdapterWithEndpoints(
                "SimpleAdapter", "default -p 5678"
            );
 
            adapter.add(new PrinterI(),
                communicator.stringToIdentity("SimplePrinter1"));
 
            adapter.add(new ScannerI(),
                communicator.stringToIdentity("SimpleScanner"));
 
            adapter.activate();
            System.out.println("Servidor Java pronto (porta 5678)...");
            communicator.waitForShutdown();
 
        } catch (LocalException e) {
            e.printStackTrace();
        }
    }
}