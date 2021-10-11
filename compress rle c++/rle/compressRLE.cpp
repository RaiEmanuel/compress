#include<iostream>
#include <fstream>

using namespace std;

int main() {
	ifstream fin; ofstream fout;
	char fileName [101], compressName[101];
	cout << "Digite o nome do arquivo a ser compactado: ";
	cin >> fileName;
	cout << "Digite o nome do arquivo compactado: ";
	cin >> compressName;
	fin.open(fileName, ios_base::in);
	fout.open(compressName, ios_base::out);
	if (!fin.is_open()) {
		cout << "Erro ao abrir o arquivo: "<<fileName<<endl;
	}

	int freq = 0; char initChar = '0';//frequencia e caractere atual lido do arquivo e
	fin >> initChar;
	if (fin.good()) freq = 1;
	else {
		cout << "Erro de leitura";
		return EXIT_SUCCESS;
	}
	char aux = '0';
	while ((fin >> aux, !fin.fail())) {
		//cout << "[" << c << "]" << endl;
		if (initChar == aux) {//continuar contagem normal, nao mudou padrao
			++freq;
		}
		else {
			//comecou novo padrao, precisa escrever no arquivo
			fout << initChar << freq;
			initChar = aux;
			freq = 1;
		}
	}

	if (freq >= 1) {
		fout << initChar << freq;
	}

	fout.close();
	fin.close();
	cout << "Compressao realizada com sucesso... Verifique a compactacao no arquivo "<<compressName<<endl;

	return EXIT_SUCCESS;
}
