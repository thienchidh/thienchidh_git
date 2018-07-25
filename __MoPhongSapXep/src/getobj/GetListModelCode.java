
package getobj;

import javax.swing.DefaultListModel;

public class GetListModelCode {

    private static DefaultListModel<String> dlmBubble;
    private static DefaultListModel<String> dlmHeap;
    private static DefaultListModel<String> dlmInsert;
    private static DefaultListModel<String> dlmInterChange;
    private static DefaultListModel<String> dlmMerge;
    private static DefaultListModel<String> dlmQuick;
    private static DefaultListModel<String> dlmSelection;
    private static DefaultListModel<String> dlmShaker;
    private static DefaultListModel<String> dlmShell;
    private static DefaultListModel<String> dlmTim;

    private GetListModelCode() {}

    private static synchronized void initBubble() {

        dlmBubble = new DefaultListModel<>();
        dlmBubble.addElement("void bubbleSort(int *a, int n)");
        dlmBubble.addElement("{");
        dlmBubble.addElement("    for(int i = 0; i < n-1; i++)");
        dlmBubble.addElement("        for(int j = n-1; j > i; j--)");
        dlmBubble.addElement("            if(a[j] < a[j-1])");
        dlmBubble.addElement("                swap(a[j], a[j-1]);");
        dlmBubble.addElement("}");

    }

    private static synchronized void initHeap() {

        dlmHeap = new DefaultListModel<>();
        dlmHeap.addElement("void heapify(int *a, int n, int i)");
        dlmHeap.addElement("{");
        dlmHeap.addElement("    int left = 2*i + 1, right = 2*i + 2, largest;");
        dlmHeap.addElement("    if(left < n && a[left] > a[i]) largest = left;");
        dlmHeap.addElement("    else largest = i;");
        dlmHeap.addElement("    if(right < n && a[right] > a[largest]) largest = right;");
        dlmHeap.addElement("    if(largest != i)");
        dlmHeap.addElement("    {");
        dlmHeap.addElement("        swap(a[i], a[largest]);");
        dlmHeap.addElement("        heapify(a, n, largest);");
        dlmHeap.addElement("    }");
        dlmHeap.addElement("}");
        dlmHeap.addElement(" ");
        dlmHeap.addElement(" ");
        dlmHeap.addElement("void buildHeap(int *a, int n)");
        dlmHeap.addElement("{");
        dlmHeap.addElement("    for(int i = n/2-1; i >= 0; i--)");
        dlmHeap.addElement("        heapify(a, n, i);");
        dlmHeap.addElement("}");
        dlmHeap.addElement(" ");
        dlmHeap.addElement(" ");
        dlmHeap.addElement("void heap(int *a, int n)");
        dlmHeap.addElement("{");
        dlmHeap.addElement("    buildHeap(a, n);");
        dlmHeap.addElement("    for(int i = n - 1; i >= 0; i--)");
        dlmHeap.addElement("    {");
        dlmHeap.addElement("        swap(a[i], a[0]);");
        dlmHeap.addElement("        heapify(a, i, 0);");
        dlmHeap.addElement("    }");
        dlmHeap.addElement("}");
        dlmHeap.addElement(" ");
        dlmHeap.addElement(" ");

    }

    private static synchronized void initInsert() {

        dlmInsert = new DefaultListModel<>();
        dlmInsert.addElement("void insertionSort(int *a, int n)");
        dlmInsert.addElement("{");
        dlmInsert.addElement("    int pos, x;");
        dlmInsert.addElement(" ");
        dlmInsert.addElement("    for(int i = 1; i < n; i++)");
        dlmInsert.addElement("    {");
        dlmInsert.addElement("        pos = i - 1;");
        dlmInsert.addElement("        x = a[i];");
        dlmInsert.addElement(" ");
        dlmInsert.addElement("        while(pos >= 0 && a[pos] > x)");
        dlmInsert.addElement("        {");
        dlmInsert.addElement("            a[pos + 1] = a[pos];");
        dlmInsert.addElement("            pos--;");
        dlmInsert.addElement("        }");
        dlmInsert.addElement(" ");
        dlmInsert.addElement("        a[pos+1] = x;");
        dlmInsert.addElement("    }");
        dlmInsert.addElement("}");

    }

    private static synchronized void initInterChange() {

        dlmInterChange = new DefaultListModel<>();
        dlmInterChange.addElement("void interChangeSort(int *a, int n)");
        dlmInterChange.addElement("{");
        dlmInterChange.addElement("    for(int i = 0; i < n - 1; i++)");
        dlmInterChange.addElement("        for(int j = i + 1; j < n; j++)");
        dlmInterChange.addElement("            if(a[j] < a[i])");
        dlmInterChange.addElement("                swap(a[j], a[i]);");
        dlmInterChange.addElement("}");

    }

    private static synchronized void initMerge() {

        dlmMerge = new DefaultListModel<>();
        dlmMerge.addElement("void mergeSort(int *a, int left, int right)");
        dlmMerge.addElement("{");
        dlmMerge.addElement("    int m;");
        dlmMerge.addElement("    if(left < right)");
        dlmMerge.addElement("    {");
        dlmMerge.addElement("        m = (left + right) / 2;");
        dlmMerge.addElement("        mergeSort(a, left, m);");
        dlmMerge.addElement("        mergeSort(a, m + 1, right);");
        dlmMerge.addElement("        merge(a, left, m, right);");
        dlmMerge.addElement("    }");
        dlmMerge.addElement("}");
        dlmMerge.addElement(" ");
        dlmMerge.addElement(" ");
        dlmMerge.addElement("void merge(int *a, int left, int m, int right)");
        dlmMerge.addElement("    int l = left, r = m + 1, k = 0;");
        dlmMerge.addElement("    int *tmp = new int[right - left + 1];");
        dlmMerge.addElement(" ");
        dlmMerge.addElement("    while(l <= m && r <= right)");
        dlmMerge.addElement("    {");
        dlmMerge.addElement("        if(a[l] < a[r]) tmp[k++] = a[l++];");
        dlmMerge.addElement("        else tmp[k++] = a[r++];");
        dlmMerge.addElement("    }");
        dlmMerge.addElement(" ");
        dlmMerge.addElement("    while (l <= m) tmp[k++] = a[l++];");
        dlmMerge.addElement(" ");
        dlmMerge.addElement("    while (r <= right) tmp[k++] = a[r++];");
        dlmMerge.addElement(" ");
        dlmMerge.addElement("    for(int i = 0; i < k; i++)");
        dlmMerge.addElement("        a[i+left] = tmp[i];");
        dlmMerge.addElement(" ");
        dlmMerge.addElement("    delete[] tmp;");
        dlmMerge.addElement("}");
        dlmMerge.addElement(" ");
        dlmMerge.addElement(" ");

    }

    private static synchronized void initQuick() {

        dlmQuick = new DefaultListModel<>();
        dlmQuick.addElement("void quickSort(int A[], int l, int r) {");
        dlmQuick.addElement("   if (l < r) {");
        dlmQuick.addElement("           int  pivot = A[(l + r) / 2];");
        dlmQuick.addElement("           int i = l, j = r;");
        dlmQuick.addElement("           while (i < j) {");
        dlmQuick.addElement("           while (A[i] < pivot)i++;");
        dlmQuick.addElement("                   while (A[j] > pivot)j--;");
        dlmQuick.addElement(" ");
        dlmQuick.addElement("                   if (i <= j) {");
        dlmQuick.addElement("                           swap(A[i], A[j]);");
        dlmQuick.addElement("                           i++; j--;");
        dlmQuick.addElement("                   }");
        dlmQuick.addElement("           }");
        dlmQuick.addElement("           if (l < j)\tquickSort(A, l, j);");
        dlmQuick.addElement("           if (i < r) quickSort(A, i, r);");
        dlmQuick.addElement("   }");
        dlmQuick.addElement("}");
        dlmQuick.addElement(" ");
        dlmQuick.addElement(" ");

    }

    private static synchronized void initSelection() {

        dlmSelection = new DefaultListModel<>();
        dlmSelection.addElement("void selectionSort(int *a, int n)");
        dlmSelection.addElement("{");
        dlmSelection.addElement("    for(int i = 0; i < n - 1; i++)");
        dlmSelection.addElement("    {");
        dlmSelection.addElement("        int idmin = i;");
        dlmSelection.addElement(" ");
        dlmSelection.addElement("        for(int j = i + 1; j < n; j++)");
        dlmSelection.addElement("            if(a[j] < a[idmin]) ");
        dlmSelection.addElement("                idmin = j;");
        dlmSelection.addElement(" ");
        dlmSelection.addElement("        swap(a[i], a[idmin]);");
        dlmSelection.addElement(" ");
        dlmSelection.addElement("    }");
        dlmSelection.addElement("}");
        dlmSelection.addElement(" ");
        dlmSelection.addElement(" ");

    }

    private static synchronized void initShaker() {

        dlmShaker = new DefaultListModel<>();
        dlmShaker.addElement("void shakerSort(int *a, int n)");
        dlmShaker.addElement("{");
        dlmShaker.addElement("    int left, right, k;");
        dlmShaker.addElement(" ");
        dlmShaker.addElement("    left = 0;");
        dlmShaker.addElement("    right = n-1;");
        dlmShaker.addElement("    k = n-1;");
        dlmShaker.addElement(" ");
        dlmShaker.addElement("    while(left < right)");
        dlmShaker.addElement("    {");
        dlmShaker.addElement("        for(int j = right; j > left; j--)");
        dlmShaker.addElement("        {");
        dlmShaker.addElement("            if(a[j] < a[j-1])");
        dlmShaker.addElement("            {");
        dlmShaker.addElement("                swap(a[j], a[j-1]); ");
        dlmShaker.addElement("                k = j;");
        dlmShaker.addElement("            }");
        dlmShaker.addElement("        }");
        dlmShaker.addElement("        left = k;");
        dlmShaker.addElement(" ");
        dlmShaker.addElement("        for (int j = left; j < right; j++)");
        dlmShaker.addElement("        {");
        dlmShaker.addElement("            if (a[j] > a[j+1])");
        dlmShaker.addElement("            { ");
        dlmShaker.addElement("                swap(a[j], a[j+1]); ");
        dlmShaker.addElement("                k = j;");
        dlmShaker.addElement("            }");
        dlmShaker.addElement("        } ");
        dlmShaker.addElement("        right = k;");
        dlmShaker.addElement("    }");
        dlmShaker.addElement("}");
        dlmShaker.addElement(" ");
        dlmShaker.addElement(" ");

    }

    private static synchronized void initShell() {

        dlmShell = new DefaultListModel<>();
        dlmShell.addElement("void shellSort(int arr[], int n)");
        dlmShell.addElement("{");
        dlmShell.addElement("    for (int gap = n/2; gap > 0; gap /= 2)");
        dlmShell.addElement("    {");
        dlmShell.addElement("        for (int i = gap; i < n; i += 1)");
        dlmShell.addElement("        {");
        dlmShell.addElement("            int temp = arr[i];");
        dlmShell.addElement("            int j;");
        dlmShell.addElement("            for (j = i; j >= gap && arr[j - gap] > temp; j -= gap)");
        dlmShell.addElement("                arr[j] = arr[j - gap];");
        dlmShell.addElement("            arr[j] = temp;");
        dlmShell.addElement("        }");
        dlmShell.addElement("    }");
        dlmShell.addElement("}");
        dlmShell.addElement(" ");
        dlmShell.addElement(" ");

    }

    private static synchronized void initTim() {

        dlmTim = new DefaultListModel<>();
        dlmTim.addElement("void insertionSort(int *a, int left, int right)");
        dlmTim.addElement("{");
        dlmTim.addElement("    int pos, x;");
        dlmTim.addElement(" ");
        dlmTim.addElement("    for(int i = left; i <= right; i++)");
        dlmTim.addElement("    {");
        dlmTim.addElement("        pos = i - 1;");
        dlmTim.addElement("        x = a[i];");
        dlmTim.addElement(" ");
        dlmTim.addElement("        while(pos >= l && a[pos] > x)");
        dlmTim.addElement("        {");
        dlmTim.addElement("            a[pos + 1] = a[pos];");
        dlmTim.addElement("            pos--;");
        dlmTim.addElement("        }");
        dlmTim.addElement(" ");
        dlmTim.addElement("        a[pos+1] = x;");
        dlmTim.addElement("    }");
        dlmTim.addElement("}");
        dlmTim.addElement(" ");
        dlmTim.addElement(" ");
        dlmTim.addElement("void merge(int *a, int left, int m, int right)");
        dlmTim.addElement("    int l = left, r = m + 1, k = 0;");
        dlmTim.addElement("    int *tmp = new int[right - left + 1];");
        dlmTim.addElement(" ");
        dlmTim.addElement("    while(l <= m && r <= right)");
        dlmTim.addElement("    {");
        dlmTim.addElement("        if(a[l] < a[r]) tmp[k++] = a[l++];");
        dlmTim.addElement("        else tmp[k++] = a[r++];");
        dlmTim.addElement("    }");
        dlmTim.addElement(" ");
        dlmTim.addElement("    while (l <= m) tmp[k++] = a[l++];");
        dlmTim.addElement(" ");
        dlmTim.addElement("    while (r <= right) tmp[k++] = a[r++];");
        dlmTim.addElement(" ");
        dlmTim.addElement("    for(int i = 0; i < k; i++)");
        dlmTim.addElement("        a[i+left] = tmp[i];");
        dlmTim.addElement(" ");
        dlmTim.addElement("    delete[] tmp;");
        dlmTim.addElement("}");
        dlmTim.addElement(" ");
        dlmTim.addElement(" ");
        dlmTim.addElement("void timSort(int arr[], int n)");
        dlmTim.addElement("{");
        dlmTim.addElement("    for (int i = 0; i < n; i+=RUN)");
        dlmTim.addElement("        insertionSort(arr, i, min((i+31), (n-1)));");
        dlmTim.addElement("    for (int size = RUN; size < n; size = 2*size)");
        dlmTim.addElement("    {");
        dlmTim.addElement("        for (int left = 0; left < n; left += 2*size)");
        dlmTim.addElement("        {");
        dlmTim.addElement("            int mid = left + size - 1;");
        dlmTim.addElement("            int right = min((left + 2*size - 1), (n-1));");
        dlmTim.addElement("            merge(arr, left, mid, right);");
        dlmTim.addElement("        }");
        dlmTim.addElement("    }");
        dlmTim.addElement("}");
        dlmTim.addElement(" ");
        dlmTim.addElement(" ");
    }

    public static synchronized DefaultListModel<String> getDlmBubble() {

        if(dlmBubble == null) {
            initBubble();
        }
        return dlmBubble;
    }

    public static synchronized DefaultListModel<String> getDlmHeap() {

        if(dlmHeap == null) {
            initHeap();
        }
        return dlmHeap;
    }

    public static synchronized DefaultListModel<String> getDlmInsert() {

        if(dlmInsert == null) {
            initInsert();
        }
        return dlmInsert;
    }

    public static synchronized DefaultListModel<String> getDlmInterChange() {

        if(dlmInterChange == null) {
            initInterChange();
        }
        return dlmInterChange;
    }

    public static synchronized DefaultListModel<String> getDlmMerge() {

        if(dlmMerge == null) {
            initMerge();
        }
        return dlmMerge;
    }

    public static synchronized DefaultListModel<String> getDlmQuick() {

        if(dlmQuick == null) {
            initQuick();
        }
        return dlmQuick;
    }

    public static synchronized DefaultListModel<String> getDlmSelection() {

        if(dlmSelection == null) {
            initSelection();
        }
        return dlmSelection;
    }

    public static synchronized DefaultListModel<String> getDlmShaker() {

        if(dlmShaker == null) {
            initShaker();
        }
        return dlmShaker;
    }

    public static synchronized DefaultListModel<String> getDlmShell() {

        if(dlmShell == null) {
            initShell();
        }
        return dlmShell;
    }

    public static synchronized DefaultListModel<String> getDlmTim() {

        if(dlmTim == null) {
            initTim();
        }
        return dlmTim;
    }
}
