module Demo
{
    interface Printer
    {
        string printString(string s);
        string printUpperCase(string s);
        string printCount(string s);
    }

    interface Scanner
    {
        string scanDocument(string filename);
        string getStatus();
        void   resetScanner();
    }
}
