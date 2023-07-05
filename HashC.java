import java.util.ArrayList;

public class HashC<E extends Comparable<E>> {
    protected class Element {
        int mark;
        Register<E> reg;

        public Element(int mark, Register<E> reg) {
            this.mark = mark;
            this.reg = reg;
        }
    }

    protected ArrayList<Element> table;
    protected int m;

    public HashC(int n) {
        this.m = n;  // calcular el primo cercano a n y asignarlo a m
        this.table = new ArrayList<>(m);
        for (int i = 0; i < m; i++)
            this.table.add(new Element(0, null));
    }

    private int functionHash(int key) {
        int foldingSize = 2; // Tamaño de cada bloque en el pliegue
        int numBlocks = Integer.toString(key).length() / foldingSize;
        int hash = 0;

        for (int i = 0; i < numBlocks; i++) {
            int block = key % (int) Math.pow(10, foldingSize);
            key /= (int) Math.pow(10, foldingSize);
            hash += block;
        }

        // Aplicar la multiplicación
        double A = 0.6180339887; // Constante áurea
        double product = A * hash;

        // Obtener la parte decimal del producto y multiplicarla por el tamaño de la tabla
        double fraction = product - Math.floor(product);
        int hashAddress = (int) (fraction * m);

        return hashAddress;
    }

    private int linearProbing(int hashAddress, int key) {
        int address = hashAddress;

        while (table.get(address).mark == 1 && table.get(address).reg.getKey() != key) {
            address = (address + 1) % m; // Avanzar linealmente hasta encontrar una posición vacía o el elemento buscado
        }

        return address;
    }

    public void insert(int key, E regValue) {
        int hashAddress = functionHash(key);
        int address = linearProbing(hashAddress, key);

        table.set(address, new Element(1, new Register<>(key, regValue)));
    }

    public E search(int key) {
        int hashAddress = functionHash(key);
        int address = linearProbing(hashAddress, key);

        if (table.get(address).mark == 1) {
            Register<E> reg = table.get(address).reg;
            if (reg.getKey() == key) {
                return reg.value;
            }
        }

        return null;
    }

    public String toString() {
        String s = "D.Real\tD.Hash\tRegister\n";
        int i = 0;
        for (Element item : table) {
            s += (i++) + "-->\t";
            if (item.mark == 1)
                s += functionHash(item.reg.key) + "\t" + item.reg + "\n";
            else
                s += "empty\n";
        }
        return s;
    }
}


