
public class Test {
    public static void main(String[] args) {
        HashC<String> hashTable = new HashC<>(11);

        // Insertar elementos en la tabla hash
        hashTable.insert(34, "Registro1");
        hashTable.insert(4, "Registro2");
        hashTable.insert(7, "Registro3");
        hashTable.insert(30, "Registro4");
        hashTable.insert(11, "Registro5");
        hashTable.insert(8, "Registro6");
        hashTable.insert(7, "Registro7");
        hashTable.insert(23, "Registro8");
        hashTable.insert(41, "Registro9");
        hashTable.insert(16, "Registro10");
        hashTable.insert(34, "Registro11");

        // Buscar un elemento en la tabla hash
        int keyToSearch = 7;
        String result = hashTable.search(keyToSearch);
        if (result != null) {
            System.out.println("Elemento encontrado: " + result);
        } else {
            System.out.println("Elemento no encontrado");
        }

        // Imprimir la tabla hash
        System.out.println(hashTable);
    }
}

