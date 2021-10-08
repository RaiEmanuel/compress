#include<iostream>
#include <map>
#include <string>
#include <fstream>
using namespace std;
/*
BEGIN
s = NIL;
while not EOF
	{
		k = next input code;
		entry = dictionary entry for k;
		output entry;
		if (s != NIL)
			add string s + entry[0] to dictionary
			with a new code;
		s = entry;
	}
END
*/
int main() {
	map<int, string> table;
	//table apenas para os codeword que ultrapassarem 255
	string s = "";//controle dos codeword para a tabela
	int k; //lookahed dos inteiros codificados
	string entry; //valor lido da tabela para printar
	int counter = 256;//conta a posicao da tabela para se usar
	char compressedName[101], fileName[101];
	
	ifstream fin; ofstream fout;
	cout << "Digite nome do arquivo compactado: ";
	cin >> compressedName;
	cout << "Digite nome do arquivo final: ";
	cin >> fileName;

	fin.open(compressedName, ios_base::in);
	fout.open(fileName, ios_base::out);
	
	if (!fin.is_open()) {
		cout << "Erro ao abrir o arquivo" << endl;
		return EXIT_SUCCESS;
	}
	
	while ((fin >> k, !fin.fail())) {//le proxima string codificada
		if (k < 256) entry = char(k);
		else entry = table[k];
		if (entry.compare("") == 0)
			entry = s + s[0];
		//cout << entry;
		fout << entry;

		if (s.compare("") != 0) {//entra a partir da segunda execucao
			string aux = s + entry[0];
			table.emplace(counter, aux);
			++counter;
		}
		s = entry;
	}
	//ultima execucao porque good sai do loop quando sem \n
	/*if (k < 256) entry = char(k);
	else entry = table[k];
	if (entry.compare("") == 0)
		entry = s + s[0];
	cout << entry;*/
	//fim do ultimo

	fout.close();
	fin.close();
	
	/*for (auto i = table.begin(); i != table.end(); ++i) {
		cout << i->first << ":" << i->second << endl;
	}*/
	return EXIT_SUCCESS;
}

