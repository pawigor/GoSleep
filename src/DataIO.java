import java.io.*;
public class DataIO {
    private String nonce;
    private String conf;
    private String startDate;
    private int startHour;
    private int startMinute;
    private int gameMinute;

    private int maxMinGameDay;
    private int gameEnd;


    File timeIO = new File(System.getProperty("user.dir")+"\\src\\timeIO.txt");
    File config = new File(System.getProperty("user.dir")+"\\src\\config.txt");
    //File timeIO = new File("timeIO.txt");

    public void setGameMinute(int gameMinute) {
        this.gameMinute = gameMinute;
    }
    public int getGameMinute(){
        return gameMinute;
    }

    private void readNonce() {
        try {
            BufferedReader re = new BufferedReader(new FileReader(timeIO));
            nonce=String.valueOf(re.readLine());
            re = new BufferedReader(new FileReader(config));
            conf=String.valueOf(re.readLine());
            re.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void writeNonce() {
        try {
            BufferedWriter writ = new BufferedWriter(new FileWriter(timeIO));
            ///проверить насколько текущие данные записывает!!!
            writ.write(nonce);
            writ.flush();
            writ.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DataIO() {
        readNonce();
        if (nonce==null)return;
        if (nonce.equals("-1"))return;

        String[] s=nonce.split(" ");
        startDate=s[0];
        startHour=Integer.parseInt(s[1]);
        startMinute=Integer.parseInt(s[2]);
        gameMinute=Integer.parseInt(s[3]);
        maxMinGameDay=Integer.parseInt(conf.split(" ")[0]);
        if (startDate.equals("SUNDAY")||startDate.equals("SATURDAY"))maxMinGameDay+=60;
        gameEnd=Integer.parseInt(conf.split(" ")[1]);
    }

    public int getMaxMinGameDay() {return maxMinGameDay;    }
    public void setMaxMinGameDay(int maxMinGameDay) {this.maxMinGameDay = maxMinGameDay;    }
    public int getGameEnd() {return gameEnd;}
    public void setGameEnd(int gameEnd) {this.gameEnd = gameEnd;    }

    public DataIO(String startDate, int startHour, int startMinute) {
        this.startDate = startDate;
        this.startHour = startHour;
        this.startMinute = startMinute;

    }
    public void setCurent(String curentDay,int currentHour,int currentMinute,int gameMinute){
        setStartDate(curentDay);
        setStartHour(currentHour);
        setStartMinute(currentMinute);
        setGameMinute(gameMinute);
        nonce=curentDay+" "+currentHour+" "+currentMinute+" "+gameMinute;
        writeNonce();
    }

    public String getStartDate() {
        //readNonce();
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
        //writeNonce();
    }
    public int getStartHour() {
        return startHour;
    }
    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }
    public int getStartMinute() {
        return startMinute;
    }
    public void setStartMinute(int startMinute) {
        this.startMinute = startMinute;
    }
    public String getNonce() {
        readNonce();
        return nonce;
    }
    public void setNonce(String nonce) {
        this.nonce = nonce;
        writeNonce();
    }
}
