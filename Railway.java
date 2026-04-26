import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.util.*;
import javax.swing.*;

public class Railway {

    static JFrame frame;
    static ArrayList<Train> trains = new ArrayList<>();

    static HashMap<String, Integer> seatAvailability = new HashMap<>();
    static HashMap<String, Integer> priceMap = new HashMap<>();

   static String[] stations = {
            "Bhubaneswar","Mumbai","New Delhi",
            "Anand Vihar Terminal","Puri","Howrah",
            "Sealdah","Santragachi","Kolkata","Shalimar",
            "Trivandrum","Bhopal","Darbhanga","Patna",
            "Nagpur","Jaipur","Coimbatore","Hyderabad",
            "Indore","Surat","Chandigarh","Visakhapatnam",
            "Amritsar","Guwahati","Ranchi","Kochi",
   };
    static class Train {
        int no;
        String name, from, to, time, fare;

        Train(int no, String name, String from, String to, String time, String fare) {
            this.no = no;
            this.name = name;
            this.from = from;
            this.to = to;
            this.time = time;
            this.fare = fare;
        }
    }

    
    public static void main(String[] args) {

    // 🔴 DATABASE CONNECTION TEST (ADD HERE)
    Connection con = DBConnection.getConnection();
    if(con != null){
        System.out.println("Connected to DB ✅");
    } else {
        System.out.println("Connection Failed ❌");
    }
        loadTrains();
        loginUI();
    }

    // ================= LOGIN (ULTRA PRO + BRANDING) =================
static void loginUI() {

    JFrame frame = new JFrame("IRCTC Login");
    frame.setSize(420, 550);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // 🌈 Gradient Background
    JPanel background = new JPanel() {
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            GradientPaint gp = new GradientPaint(
                    0, 0, new Color(0, 102, 204),
                    0, getHeight(), new Color(0, 204, 204)
            );
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    };
    background.setLayout(new GridBagLayout());

    // 💎 Glass Panel
    JPanel glass = new JPanel();
    glass.setPreferredSize(new Dimension(320, 400));
    glass.setBackground(new Color(255, 255, 255, 180));
    glass.setLayout(null);
    glass.setBorder(BorderFactory.createLineBorder(new Color(255,255,255,120),1,true));

    // ================= 🚆 LOGO + BRAND =================

    JLabel logo = new JLabel("🚆", SwingConstants.CENTER);
    logo.setBounds(135, 20, 50, 40);
    logo.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 28));
    glass.add(logo);

    JLabel brand = new JLabel("IRCTC", SwingConstants.CENTER);
    brand.setBounds(110, 60, 100, 25);
    brand.setFont(new Font("Segoe UI", Font.BOLD, 20));
    brand.setForeground(new Color(0, 51, 153));
    glass.add(brand);

    JLabel subtitle = new JLabel("Indian Railway Booking", SwingConstants.CENTER);
    subtitle.setBounds(60, 85, 200, 20);
    subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
    subtitle.setForeground(Color.DARK_GRAY);
    glass.add(subtitle);

    // ================= 👤 USER =================
    JLabel userIcon = new JLabel("👤");
    userIcon.setBounds(30, 130, 30, 30);
    glass.add(userIcon);

    JTextField userField = new JTextField();
    userField.setBounds(60, 130, 220, 35);
    userField.setBorder(BorderFactory.createLineBorder(Color.GRAY,1,true));
    glass.add(userField);

    // ================= 🔒 PASS =================
    JLabel passIcon = new JLabel("🔒");
    passIcon.setBounds(30, 180, 30, 30);
    glass.add(passIcon);

    JPasswordField passField = new JPasswordField();
    passField.setBounds(60, 180, 220, 35);
    passField.setBorder(BorderFactory.createLineBorder(Color.GRAY,1,true));
    glass.add(passField);

    // ================= 🔵 LOGIN BUTTON =================
    JButton loginBtn = new JButton("Login");
    loginBtn.setBounds(90, 250, 140, 40);

    loginBtn.setBackground(new Color(0, 102, 204));
    loginBtn.setForeground(Color.WHITE);
    loginBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
    loginBtn.setFocusPainted(false);
    loginBtn.setBorder(BorderFactory.createEmptyBorder());
    loginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

    glass.add(loginBtn);

    // ✨ Hover Effect
    loginBtn.addMouseListener(new MouseAdapter() {
        public void mouseEntered(MouseEvent e) {
            loginBtn.setBackground(new Color(0, 80, 180));
        }
        public void mouseExited(MouseEvent e) {
            loginBtn.setBackground(new Color(0, 102, 204));
        }
    });

    // 🔐 LOGIN ACTION
    loginBtn.addActionListener(e -> {
        if(userField.getText().equals("admin") &&
           new String(passField.getPassword()).equals("1234")){
            frame.dispose();
            searchUI();
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid Login");
        }
    });

    // Footer Branding
    JLabel footer = new JLabel("Powered by Indian Railways", SwingConstants.CENTER);
    footer.setBounds(60, 310, 200, 20);
    footer.setFont(new Font("Segoe UI", Font.PLAIN, 10));
    footer.setForeground(Color.GRAY);
    glass.add(footer);

    background.add(glass);
    frame.add(background);

    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
}

    // ================= DATA =================
static void loadTrains() {

    // 🔥 NEW TRAIN DATA (replace old completely)
    trains.clear();

    trains.add(new Train(18478,"Kalinga Utkal","Bhubaneswar","Puri","1:40 AM - 3:25 AM","₹40"));
    trains.add(new Train(12840,"Howrah Mail","Bhubaneswar","Howrah","2:50 PM - 11:20 PM","₹165"));
    trains.add(new Train(20807,"Hirakud","Bhubaneswar","New Delhi","7:15 AM - 2:30 PM","₹415"));

    // ================= ALL TRAINS (COMBINED BLOCK) =================

// 🔹 Core Trains
trains.add(new Train(18478,"Kalinga Utkal","Bhubaneswar","Puri","1:40-3:25","₹40"));
trains.add(new Train(12840,"Howrah Mail","Bhubaneswar","Kolkata","2:50-11:20","₹165"));
trains.add(new Train(20807,"Hirakud","Bhubaneswar","New Delhi","7:15-14:30","₹415"));

// 🔹 Major National Trains
trains.add(new Train(12626,"Kerala Express","New Delhi","Trivandrum","11:25-15:50","₹750"));
trains.add(new Train(12002,"Shatabdi Express","New Delhi","Bhopal","06:00-14:30","₹900"));
trains.add(new Train(12951,"Mumbai Rajdhani","Mumbai","New Delhi","16:35-08:35","₹1500"));
trains.add(new Train(12801,"Purushottam Express","Puri","New Delhi","21:00-18:30","₹680"));
trains.add(new Train(12723,"Telangana Express","Hyderabad","New Delhi","06:25-08:00","₹720"));
trains.add(new Train(12622,"Tamil Nadu Express","New Delhi","Chennai","22:30-07:00","₹850"));
trains.add(new Train(12296,"Sanghamitra Express","Bangalore","Patna","09:00-19:45","₹920"));
trains.add(new Train(12860,"Gitanjali Express","Mumbai","Kolkata","06:00-22:30","₹780"));
trains.add(new Train(12615,"Grand Trunk Express","Chennai","New Delhi","19:15-06:55","₹880"));
trains.add(new Train(12566,"Bihar Sampark Kranti","New Delhi","Darbhanga","14:00-08:30","₹650"));

// 🔹 Bhubaneswar → Puri (10 trains)
trains.add(new Train(18401,"Utkal Local","Bhubaneswar","Puri","05:00-06:30","₹40"));
trains.add(new Train(18402,"Jagannath Express","Bhubaneswar","Puri","06:15-07:45","₹45"));
trains.add(new Train(18403,"Kalinga Fast","Bhubaneswar","Puri","07:30-09:00","₹50"));
trains.add(new Train(18404,"Puri Passenger","Bhubaneswar","Puri","09:00-10:40","₹35"));
trains.add(new Train(18405,"Odisha Express","Bhubaneswar","Puri","11:00-12:30","₹55"));
trains.add(new Train(18406,"Temple Link","Bhubaneswar","Puri","13:20-14:50","₹60"));
trains.add(new Train(18407,"Konark Express","Bhubaneswar","Puri","15:00-16:30","₹50"));
trains.add(new Train(18408,"Sea Breeze Local","Bhubaneswar","Puri","17:10-18:40","₹45"));
trains.add(new Train(18409,"Puri Superfast","Bhubaneswar","Puri","19:00-20:20","₹65"));
trains.add(new Train(18410,"Evening Shuttle","Bhubaneswar","Puri","21:00-22:30","₹40"));

// 🔹 Chennai → Bangalore (10)
trains.add(new Train(12601,"Brindavan Express","Chennai","Bangalore","07:00-12:30","₹120"));
trains.add(new Train(12602,"Lalbagh Express","Chennai","Bangalore","06:15-11:45","₹130"));
trains.add(new Train(12603,"Shatabdi Link","Chennai","Bangalore","05:50-10:30","₹150"));
trains.add(new Train(12604,"Tech City Express","Chennai","Bangalore","09:00-14:30","₹140"));
trains.add(new Train(12605,"Morning Star","Chennai","Bangalore","10:30-16:00","₹110"));
trains.add(new Train(12606,"Silicon Valley Express","Chennai","Bangalore","12:00-17:30","₹135"));
trains.add(new Train(12607,"Cauvery Express","Chennai","Bangalore","14:15-20:00","₹125"));
trains.add(new Train(12608,"Evening Express","Chennai","Bangalore","16:30-22:00","₹140"));
trains.add(new Train(12609,"Night Rider","Chennai","Bangalore","19:00-00:30","₹150"));
trains.add(new Train(12610,"Midnight Superfast","Chennai","Bangalore","23:00-04:30","₹160"));

// 🔹 Mumbai → Delhi (10)
trains.add(new Train(12901,"Rajdhani Express","Mumbai","Delhi","16:00-08:30","₹1500"));
trains.add(new Train(12902,"Duronto Express","Mumbai","Delhi","23:00-14:00","₹1400"));
trains.add(new Train(12903,"Garib Rath","Mumbai","Delhi","20:30-12:30","₹900"));
trains.add(new Train(12904,"August Kranti","Mumbai","Delhi","17:40-10:20","₹1300"));
trains.add(new Train(12905,"Capital Express","Mumbai","Delhi","19:00-11:00","₹1200"));
trains.add(new Train(12906,"Superfast Link","Mumbai","Delhi","21:15-13:30","₹1100"));
trains.add(new Train(12907,"Western Express","Mumbai","Delhi","06:00-22:00","₹1000"));
trains.add(new Train(12908,"Metro Connector","Mumbai","Delhi","08:30-01:00","₹1050"));
trains.add(new Train(12909,"National Express","Mumbai","Delhi","11:00-04:00","₹1150"));
trains.add(new Train(12910,"Night Rajdhani","Mumbai","Delhi","22:00-14:30","₹1550"));

// 🔹 Hyderabad → Chennai (10)
trains.add(new Train(12701,"Charminar Express","Hyderabad","Chennai","18:00-06:30","₹450"));
trains.add(new Train(12702,"Hyderabad Mail","Hyderabad","Chennai","20:00-08:30","₹420"));
trains.add(new Train(12703,"Deccan Express","Hyderabad","Chennai","06:00-18:00","₹400"));
trains.add(new Train(12704,"South Link","Hyderabad","Chennai","09:30-21:30","₹410"));
trains.add(new Train(12705,"Coastal Express","Hyderabad","Chennai","11:00-23:00","₹430"));
trains.add(new Train(12706,"Morning Express","Hyderabad","Chennai","05:30-17:30","₹390"));
trains.add(new Train(12707,"Evening Superfast","Hyderabad","Chennai","17:00-05:00","₹460"));
trains.add(new Train(12708,"Night Express","Hyderabad","Chennai","22:00-10:00","₹470"));
trains.add(new Train(12709,"Fast Passenger","Hyderabad","Chennai","13:00-01:30","₹380"));
trains.add(new Train(12710,"City Connector","Hyderabad","Chennai","15:30-03:30","₹440"));

// 🔹 Kolkata → Patna (10)
trains.add(new Train(12301,"Howrah Express","Kolkata","Patna","06:00-14:00","₹300"));
trains.add(new Train(12302,"Jan Shatabdi","Kolkata","Patna","07:30-15:30","₹350"));
trains.add(new Train(12303,"Intercity Express","Kolkata","Patna","09:00-17:00","₹320"));
trains.add(new Train(12304,"Fast Link","Kolkata","Patna","11:00-19:00","₹310"));
trains.add(new Train(12305,"Superfast Express","Kolkata","Patna","13:00-21:00","₹360"));
trains.add(new Train(12306,"Evening Express","Kolkata","Patna","15:30-23:30","₹340"));
trains.add(new Train(12307,"Night Rider","Kolkata","Patna","18:00-02:00","₹370"));
trains.add(new Train(12308,"Midnight Express","Kolkata","Patna","21:00-05:00","₹380"));
trains.add(new Train(12309,"Passenger Link","Kolkata","Patna","10:30-19:30","₹280"));
trains.add(new Train(12310,"Rapid Express","Kolkata","Patna","12:00-20:00","₹330"));

// ================= ALL TRAINS WITH RETURN ROUTES =================

// 🔹 Core Trains + Return
trains.add(new Train(18478,"Kalinga Utkal","Bhubaneswar","Puri","1:40-3:25","₹40"));
trains.add(new Train(28478,"Kalinga Utkal Return","Puri","Bhubaneswar","04:00-05:45","₹40"));

trains.add(new Train(12840,"Howrah Mail","Bhubaneswar","Kolkata","2:50-11:20","₹165"));
trains.add(new Train(22840,"Howrah Mail Return","Kolkata","Bhubaneswar","12:00-20:30","₹165"));

trains.add(new Train(20807,"Hirakud","Bhubaneswar","New Delhi","7:15-14:30","₹415"));
trains.add(new Train(30807,"Hirakud Return","New Delhi","Bhubaneswar","16:00-23:30","₹415"));


// 🔹 Bhubaneswar ↔ Puri (10 + 10 return)
trains.add(new Train(18401,"Utkal Local","Bhubaneswar","Puri","05:00-06:30","₹40"));
trains.add(new Train(28401,"Utkal Local Return","Puri","Bhubaneswar","07:00-08:30","₹40"));

trains.add(new Train(18402,"Jagannath Express","Bhubaneswar","Puri","06:15-07:45","₹45"));
trains.add(new Train(28402,"Jagannath Return","Puri","Bhubaneswar","08:15-09:45","₹45"));

trains.add(new Train(18403,"Kalinga Fast","Bhubaneswar","Puri","07:30-09:00","₹50"));
trains.add(new Train(28403,"Kalinga Fast Return","Puri","Bhubaneswar","09:30-11:00","₹50"));

trains.add(new Train(18404,"Puri Passenger","Bhubaneswar","Puri","09:00-10:40","₹35"));
trains.add(new Train(28404,"Puri Passenger Return","Puri","Bhubaneswar","11:10-12:50","₹35"));

trains.add(new Train(18405,"Odisha Express","Bhubaneswar","Puri","11:00-12:30","₹55"));
trains.add(new Train(28405,"Odisha Return","Puri","Bhubaneswar","13:00-14:30","₹55"));


// 🔹 Chennai ↔ Bangalore (10 + return)
trains.add(new Train(12601,"Brindavan Express","Chennai","Bangalore","07:00-12:30","₹120"));
trains.add(new Train(22601,"Brindavan Return","Bangalore","Chennai","13:30-19:00","₹120"));

trains.add(new Train(12602,"Lalbagh Express","Chennai","Bangalore","06:15-11:45","₹130"));
trains.add(new Train(22602,"Lalbagh Return","Bangalore","Chennai","12:30-18:00","₹130"));


// 🔹 Mumbai ↔ Delhi (10 + return)
trains.add(new Train(12901,"Rajdhani Express","Mumbai","Delhi","16:00-08:30","₹1500"));
trains.add(new Train(22901,"Rajdhani Return","Delhi","Mumbai","09:30-02:00","₹1500"));

trains.add(new Train(12902,"Duronto Express","Mumbai","Delhi","23:00-14:00","₹1400"));
trains.add(new Train(22902,"Duronto Return","Delhi","Mumbai","15:00-06:00","₹1400"));


// 🔹 Hyderabad ↔ Chennai
trains.add(new Train(12701,"Charminar Express","Hyderabad","Chennai","18:00-06:30","₹450"));
trains.add(new Train(22701,"Charminar Return","Chennai","Hyderabad","07:30-20:00","₹450"));


// 🔹 Kolkata ↔ Patna
trains.add(new Train(12301,"Howrah Express","Kolkata","Patna","06:00-14:00","₹300"));
trains.add(new Train(22301,"Howrah Return","Patna","Kolkata","15:00-23:00","₹300"));

// ================= EXTRA 20 ROUTES WITH RETURN =================

// 🔹 Pune ↔ Hyderabad
trains.add(new Train(14001,"Deccan Link","Pune","Hyderabad","06:00-14:00","₹500"));
trains.add(new Train(24001,"Deccan Return","Hyderabad","Pune","15:00-23:00","₹500"));

// 🔹 Nagpur ↔ Mumbai
trains.add(new Train(14002,"Vidarbha Express","Nagpur","Mumbai","07:00-17:00","₹650"));
trains.add(new Train(24002,"Vidarbha Return","Mumbai","Nagpur","18:00-04:00","₹650"));

// 🔹 Delhi ↔ Jaipur
trains.add(new Train(14003,"Pink City Express","Delhi","Jaipur","06:30-11:30","₹300"));
trains.add(new Train(24003,"Pink City Return","Jaipur","Delhi","12:30-17:30","₹300"));

// 🔹 Bangalore ↔ Hyderabad
trains.add(new Train(14004,"Tech Express","Bangalore","Hyderabad","05:30-13:30","₹550"));
trains.add(new Train(24004,"Tech Return","Hyderabad","Bangalore","14:30-22:30","₹550"));

// 🔹 Chennai ↔ Coimbatore
trains.add(new Train(14005,"Cauvery Link","Chennai","Coimbatore","06:00-13:00","₹400"));
trains.add(new Train(24005,"Cauvery Return","Coimbatore","Chennai","14:00-21:00","₹400"));

// 🔹 Kolkata ↔ Guwahati
trains.add(new Train(14006,"North East Express","Kolkata","Guwahati","08:00-02:00","₹800"));
trains.add(new Train(24006,"North East Return","Guwahati","Kolkata","03:00-21:00","₹800"));

// 🔹 Patna ↔ Ranchi
trains.add(new Train(14007,"Jharkhand Link","Patna","Ranchi","07:00-12:00","₹250"));
trains.add(new Train(24007,"Jharkhand Return","Ranchi","Patna","13:00-18:00","₹250"));

// 🔹 Ahmedabad ↔ Mumbai
trains.add(new Train(14008,"Western Corridor","Ahmedabad","Mumbai","06:00-12:00","₹350"));
trains.add(new Train(24008,"Western Return","Mumbai","Ahmedabad","13:00-19:00","₹350"));

// 🔹 Lucknow ↔ Delhi
trains.add(new Train(14009,"Awadh Express","Lucknow","Delhi","06:00-14:00","₹500"));
trains.add(new Train(24009,"Awadh Return","Delhi","Lucknow","15:00-23:00","₹500"));

// 🔹 Indore ↔ Bhopal
trains.add(new Train(14010,"Malwa Link","Indore","Bhopal","07:00-11:00","₹200"));
trains.add(new Train(24010,"Malwa Return","Bhopal","Indore","12:00-16:00","₹200"));

// 🔹 Surat ↔ Ahmedabad
trains.add(new Train(14011,"Diamond Link","Surat","Ahmedabad","06:30-11:30","₹250"));
trains.add(new Train(24011,"Diamond Return","Ahmedabad","Surat","12:30-17:30","₹250"));

// 🔹 Chandigarh ↔ Amritsar
trains.add(new Train(14012,"Punjab Link","Chandigarh","Amritsar","07:00-11:00","₹220"));
trains.add(new Train(24012,"Punjab Return","Amritsar","Chandigarh","12:00-16:00","₹220"));

// 🔹 Kochi ↔ Bangalore
trains.add(new Train(14013,"Kerala Tech","Kochi","Bangalore","06:00-15:00","₹600"));
trains.add(new Train(24013,"Kerala Return","Bangalore","Kochi","16:00-01:00","₹600"));

// 🔹 Trivandrum ↔ Chennai
trains.add(new Train(14014,"Southern Express","Trivandrum","Chennai","05:00-14:00","₹650"));
trains.add(new Train(24014,"Southern Return","Chennai","Trivandrum","15:00-00:00","₹650"));

// 🔹 Bhubaneswar ↔ Kolkata
trains.add(new Train(14015,"East Coast Express","Bhubaneswar","Kolkata","06:00-13:00","₹350"));
trains.add(new Train(24015,"East Coast Return","Kolkata","Bhubaneswar","14:00-21:00","₹350"));

// 🔹 Visakhapatnam ↔ Chennai
trains.add(new Train(14016,"Coastal Link","Visakhapatnam","Chennai","07:00-18:00","₹550"));
trains.add(new Train(24016,"Coastal Return","Chennai","Visakhapatnam","19:00-06:00","₹550"));

// 🔹 Delhi ↔ Chandigarh
trains.add(new Train(14017,"Capital Shuttle","Delhi","Chandigarh","06:00-10:00","₹200"));
trains.add(new Train(24017,"Capital Return","Chandigarh","Delhi","11:00-15:00","₹200"));

// 🔹 Mumbai ↔ Pune
trains.add(new Train(14018,"Intercity Express","Mumbai","Pune","06:30-10:30","₹150"));
trains.add(new Train(24018,"Intercity Return","Pune","Mumbai","11:30-15:30","₹150"));

// 🔹 Hyderabad ↔ Nagpur
trains.add(new Train(14019,"Central Link","Hyderabad","Nagpur","07:00-15:00","₹500"));
trains.add(new Train(24019,"Central Return","Nagpur","Hyderabad","16:00-00:00","₹500"));

// 🔹 Jaipur ↔ Delhi
trains.add(new Train(14020,"Royal Express","Jaipur","Delhi","08:00-13:00","₹320"));
trains.add(new Train(24020,"Royal Return","Delhi","Jaipur","14:00-19:00","₹320"));

    // 🔥 NEW SEAT DATA
seatAvailability.clear();
seatAvailability.put("1AC", 18);
seatAvailability.put("2AC", 120);
seatAvailability.put("3AC", 250);
seatAvailability.put("3AC Economy", 180);
seatAvailability.put("Sleeper", 466);
seatAvailability.put("AC Chair Car", 98);
seatAvailability.put("AC Chair Car Economy", 11);
seatAvailability.put("2S", 25);

// 🔥 NEW PRICE DATA
priceMap.clear();
priceMap.put("1AC", 7000);
priceMap.put("2AC", 4000);
priceMap.put("3AC", 3000);
priceMap.put("3AC Economy", 2700);
priceMap.put("Sleeper", 680);
priceMap.put("AC Chair Car", 1300);
priceMap.put("AC Chair Car Economy", 1500);
priceMap.put("2S", 300);
}

// 🔥 PNR
static String generatePNR() {
    Random r = new Random();
    return "" + (1000000000L + (long)(r.nextDouble() * 9000000000L));
}

// 🚆 Seat Map
static void seatMapUI(String coachType) {

    JFrame seatFrame = new JFrame("Coach Layout - " + coachType);
    seatFrame.setSize(500,600);

    JPanel main = new JPanel();
    main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

    int totalSeats = 64;

    for(int i=1; i<=totalSeats; i+=8){

        JPanel row = new JPanel(new GridLayout(1,8,5,5));

        String[] types = {"LB","MB","UB","LB","MB","UB","SL","SU"};

        for(int j=0; j<8; j++){

            int seatNo = i + j;
            if(seatNo > totalSeats) break;

            JToggleButton seat = new JToggleButton(
                    "<html>"+seatNo+"<br>"+types[j]+"</html>"
            );

            if(Math.random() < 0.25){
                seat.setEnabled(false);
                seat.setBackground(Color.RED);
            } else {
                seat.setBackground(Color.GREEN);
            }

            seat.addActionListener(e -> {
                if(seat.isSelected()){
                    seat.setBackground(Color.YELLOW);
                } else {
                    seat.setBackground(Color.GREEN);
                }
            });

            row.add(seat);
        }

        main.add(row);
        main.add(Box.createRigidArea(new Dimension(0,10)));
    }

    seatFrame.add(new JScrollPane(main));
    seatFrame.setVisible(true);
}

    // ================= SEARCH (GLASS UI LIKE LOGIN) =================
static void searchUI() {

    JFrame search = new JFrame("Search Train");
    search.setSize(500,600);
    search.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // 🌈 Gradient Background
    JPanel bg = new JPanel() {
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            GradientPaint gp = new GradientPaint(
                    0, 0, new Color(0,102,204),
                    0, getHeight(), new Color(0,204,204)
            );
            g2d.setPaint(gp);
            g2d.fillRect(0,0,getWidth(),getHeight());
        }
    };
    bg.setLayout(new GridBagLayout());

    // 💎 Glass Panel
    JPanel glass = new JPanel();
    glass.setPreferredSize(new Dimension(350,450));
    glass.setBackground(new Color(255,255,255,180));
    glass.setLayout(new BoxLayout(glass, BoxLayout.Y_AXIS));
    glass.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

    // ===== FROM =====
    JLabel fromLabel = new JLabel("From");
    JComboBox<String> from = new JComboBox<>(stations);
    from.setEditable(true);
    from.setMaximumSize(new Dimension(300,35));

    // ===== TO =====
    JLabel toLabel = new JLabel("To");
    JComboBox<String> to = new JComboBox<>(stations);
    to.setEditable(true);
    to.setMaximumSize(new Dimension(300,35));

    // 🔁 Swap Button
    JButton swap = new JButton("🔁 Swap");
    swap.setAlignmentX(Component.CENTER_ALIGNMENT);
    swap.setBackground(new Color(0,102,204));
    swap.setForeground(Color.WHITE);
    swap.setFocusPainted(false);

    swap.addActionListener(e -> {
        String temp = from.getEditor().getItem().toString();
        from.getEditor().setItem(to.getEditor().getItem());
        to.getEditor().setItem(temp);
    });

    // 🤖 Auto Suggest
    KeyAdapter autoSuggest = new KeyAdapter() {
        public void keyReleased(KeyEvent e) {
            JComboBox box = (JComboBox) e.getSource();
            String text = box.getEditor().getItem().toString().toLowerCase();

            box.removeAllItems();
            for(String s : stations){
                if(s.toLowerCase().contains(text)){
                    box.addItem(s);
                }
            }

            box.getEditor().setItem(text);
            box.showPopup();
        }
    };

    ((JTextField)from.getEditor().getEditorComponent()).addKeyListener(autoSuggest);
    ((JTextField)to.getEditor().getEditorComponent()).addKeyListener(autoSuggest);

    // ===== DATE (FIXED SMALL BOX) =====
    JPanel datePanel = new JPanel(new BorderLayout());
    datePanel.setMaximumSize(new Dimension(300,50));

    JTextField dateField = new JTextField();
    dateField.setEditable(false);
    dateField.setBorder(BorderFactory.createTitledBorder("Journey Date"));

    JButton calendarBtn = new JButton("📅");

    datePanel.add(dateField, BorderLayout.CENTER);
    datePanel.add(calendarBtn, BorderLayout.EAST);

    // 📅 Calendar Popup
    calendarBtn.addActionListener(e -> {

    JDialog calDialog = new JDialog(search, "Select Date", true);
    calDialog.setSize(400,350);
    calDialog.setLayout(new BorderLayout());

    JPanel top = new JPanel();

    String[] months = {
        "Jan","Feb","Mar","Apr","May","Jun",
        "Jul","Aug","Sep","Oct","Nov","Dec"
    };

    JComboBox<String> monthBox = new JComboBox<>(months);
    JComboBox<Integer> yearBox = new JComboBox<>(new Integer[]{2026, 2027});

    top.add(new JLabel("Month:"));
    top.add(monthBox);
    top.add(new JLabel("Year:"));
    top.add(yearBox);

    JPanel calPanel = new JPanel(new GridLayout(7,7,5,5));

    Runnable drawCalendar = () -> {

        calPanel.removeAll();

        String[] days = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
        for(String d: days){
            calPanel.add(new JLabel(d, SwingConstants.CENTER));
        }

        int month = monthBox.getSelectedIndex();
        int year = (int) yearBox.getSelectedItem();

        Calendar cal = Calendar.getInstance();
        cal.set(year, month, 1);

        int firstDay = cal.get(Calendar.DAY_OF_WEEK);

        // ✅ CORRECT DAYS LOGIC
        int daysInMonth;

        if(month == 1){ // February
            // Leap year check
            if((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)){
                daysInMonth = 29;
            } else {
                daysInMonth = 28;
            }
        }
        else if(month == 3 || month == 5 || month == 8 || month == 10){
            daysInMonth = 30;
        }
        else{
            daysInMonth = 31;
        }

        // Empty spaces before start
        for(int i=1; i<firstDay; i++){
            calPanel.add(new JLabel(""));
        }

        // Days
        for(int i=1; i<=daysInMonth; i++){
            JButton dayBtn = new JButton(String.valueOf(i));

            int day = i;
            dayBtn.addActionListener(ev -> {
                dateField.setText(day + "-" + (month+1) + "-" + year);
                calDialog.dispose();
            });

            calPanel.add(dayBtn);
        }

        calPanel.revalidate();
        calPanel.repaint();
    };

    // 🔁 Refresh when month/year changes
    monthBox.addActionListener(e2 -> drawCalendar.run());
    yearBox.addActionListener(e2 -> drawCalendar.run());

    drawCalendar.run();

    calDialog.add(top, BorderLayout.NORTH);
    calDialog.add(calPanel, BorderLayout.CENTER);
    calDialog.setLocationRelativeTo(search);
    calDialog.setVisible(true);
});

    // 🔍 Search Button
    JButton searchBtn = new JButton("Search Trains");
    searchBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
    searchBtn.setBackground(new Color(0,102,204));
    searchBtn.setForeground(Color.WHITE);
    searchBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));

    searchBtn.addActionListener(e -> {
        String fromVal = from.getEditor().getItem().toString();
        String toVal = to.getEditor().getItem().toString();
        showTrains(fromVal, toVal);
    });

    // ===== ADD COMPONENTS =====
    glass.add(fromLabel);
    glass.add(from);

    glass.add(Box.createRigidArea(new Dimension(0,10)));
    glass.add(swap);

    glass.add(Box.createRigidArea(new Dimension(0,10)));
    glass.add(toLabel);
    glass.add(to);

    glass.add(Box.createRigidArea(new Dimension(0,15)));
    glass.add(datePanel);

    glass.add(Box.createRigidArea(new Dimension(0,20)));
    glass.add(searchBtn);

    bg.add(glass);
    search.add(bg);

    search.setLocationRelativeTo(null);
    search.setVisible(true);
}
    // ================= TRAIN LIST =================
    static void showTrains(String from, String to) {

    frame = new JFrame(from + " → " + to);
    frame.setSize(500,650);

    // 🌈 Gradient background
    JPanel background = new JPanel() {
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            GradientPaint gp = new GradientPaint(
                    0, 0, new Color(0,102,204),
                    0, getHeight(), new Color(0,204,204)
            );
            g2d.setPaint(gp);
            g2d.fillRect(0,0,getWidth(),getHeight());
        }
    };
    background.setLayout(new BorderLayout());

    // 💎 Glass panel
    JPanel glass = new JPanel();
    glass.setBackground(new Color(255,255,255,180));
    glass.setLayout(new BoxLayout(glass, BoxLayout.Y_AXIS));

    JScrollPane scroll = new JScrollPane(glass);
    scroll.setBorder(null);
    scroll.setOpaque(false);
    scroll.getViewport().setOpaque(false);

    // ================= SORT =================
    JPanel topBar = new JPanel();
    topBar.setBackground(new Color(255,255,255,180));

    String[] sortOptions = {"Sort by Time","Sort by Price"};
    JComboBox<String> sortBox = new JComboBox<>(sortOptions);

    topBar.add(new JLabel("Sort: "));
    topBar.add(sortBox);

    background.add(topBar, BorderLayout.NORTH);
    background.add(scroll, BorderLayout.CENTER);

    // 🔄 Filter trains
    ArrayList<Train> filtered = new ArrayList<>();

    for(Train t : trains){
        if(t.from.equalsIgnoreCase(from) && t.to.equalsIgnoreCase(to)){
            filtered.add(t);
        }
    }

    // 🔄 Sorting logic
    sortBox.addActionListener(e -> {
        String selected = sortBox.getSelectedItem().toString();

        if(selected.contains("Time")){
            filtered.sort(Comparator.comparing(t -> t.time));
        } else {
            filtered.sort(Comparator.comparingInt(t ->
                    Integer.parseInt(t.fare.replace("₹",""))));
        }

        refreshTrainList(glass, filtered);
    });

    // First load
    refreshTrainList(glass, filtered);

    frame.add(background);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
}

static void refreshTrainList(JPanel glass, ArrayList<Train> list){

    glass.removeAll();
    ButtonGroup group = new ButtonGroup();

    for(Train t : list){

        JPanel card = new JPanel(new BorderLayout());
        card.setMaximumSize(new Dimension(400,110));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,1,true));

        // ✨ Hover effect
        card.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e){
                card.setBackground(new Color(230,245,255));
            }
            public void mouseExited(MouseEvent e){
                card.setBackground(Color.WHITE);
            }
        });

        // 🚆 LEFT PANEL
        JPanel left = new JPanel();
        left.setBackground(Color.WHITE);
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));

        JLabel icon = new JLabel("🚆");
        icon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 22));

        JLabel name = new JLabel(t.no + " " + t.name);
        name.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JLabel fare = new JLabel(t.fare);
        fare.setForeground(new Color(0,150,0));

        left.add(icon);
        left.add(name);
        left.add(fare);

        // ⏰ RIGHT TIME
        JLabel time = new JLabel(t.time);

        // 🔘 SELECT
        JRadioButton select = new JRadioButton();
        group.add(select);

        // 🔘 BOOK BUTTON
        JButton book = new JButton("Book");
        book.setEnabled(false);
        book.setBackground(new Color(0,102,204));
        book.setForeground(Color.WHITE);

        select.addActionListener(e -> book.setEnabled(true));
        book.addActionListener(e -> bookingUI(t));

        JPanel bottom = new JPanel();
        bottom.setBackground(Color.WHITE);
        bottom.add(book);

        // ADD
        card.add(select, BorderLayout.WEST);
        card.add(left, BorderLayout.CENTER);
        card.add(time, BorderLayout.EAST);
        card.add(bottom, BorderLayout.SOUTH);

        glass.add(Box.createRigidArea(new Dimension(0,10)));
        glass.add(card);
    }

    glass.revalidate();
    glass.repaint();
}
    // ================= BOOKING =================
    static void bookingUI(Train t) {

    JFrame book = new JFrame("Booking");
    book.setSize(520,700);

    // 🌈 Background
    JPanel bg = new JPanel() {
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            GradientPaint gp = new GradientPaint(
                    0,0,new Color(0,102,204),
                    0,getHeight(),new Color(0,204,204)
            );
            g2.setPaint(gp);
            g2.fillRect(0,0,getWidth(),getHeight());
        }
    };
    bg.setLayout(new GridBagLayout());

    // 💎 Glass Panel
    JPanel glass = new JPanel();
    glass.setPreferredSize(new Dimension(450,640));
    glass.setBackground(new Color(255,255,255,200));
    glass.setLayout(new BoxLayout(glass, BoxLayout.Y_AXIS));
    glass.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

    // ================= TITLE =================
    JLabel title = new JLabel("Passenger Details");
    title.setFont(new Font("Segoe UI", Font.BOLD, 18));
    title.setAlignmentX(Component.CENTER_ALIGNMENT);

    // ================= PASSENGERS =================
    ArrayList<JTextField> names = new ArrayList<>();
    ArrayList<JTextField> ages = new ArrayList<>();
    ArrayList<JTextField> phones = new ArrayList<>();
    ArrayList<JTextField> aadhars = new ArrayList<>();

    JPanel passengerPanel = new JPanel();
    passengerPanel.setLayout(new BoxLayout(passengerPanel, BoxLayout.Y_AXIS));
    passengerPanel.setOpaque(false);

    Runnable addPassenger = () -> {

        JPanel row = new JPanel(new GridLayout(2,4,8,8));

        row.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200,200,200),1,true),
            BorderFactory.createEmptyBorder(8,8,8,8)
        ));

        row.setOpaque(true);
        row.setBackground(Color.WHITE);

        // ✨ Hover
        row.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e){
                row.setBackground(new Color(230,245,255));
            }
            public void mouseExited(MouseEvent e){
                row.setBackground(Color.WHITE);
            }
        });

        JTextField name = new JTextField();
        JTextField age = new JTextField();
        JTextField phone = new JTextField();
        JTextField aadhar = new JTextField();

        row.add(new JLabel("Name"));
        row.add(new JLabel("Age"));
        row.add(new JLabel("Phone"));
        row.add(new JLabel("Aadhar"));

        row.add(name);
        row.add(age);
        row.add(phone);
        row.add(aadhar);

        passengerPanel.add(row);
        passengerPanel.add(Box.createRigidArea(new Dimension(0,10)));

        names.add(name);
        ages.add(age);
        phones.add(phone);
        aadhars.add(aadhar);

        passengerPanel.revalidate();
    };

    addPassenger.run();

    JButton addBtn = new JButton("+ Add Passenger");
    addBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
    addBtn.setBackground(new Color(255,153,0));
    addBtn.setForeground(Color.WHITE);
    addButtonEffect(addBtn);

    addBtn.addActionListener(e -> addPassenger.run());

    

    // ================= CLASS =================
    JLabel classLabel = new JLabel("Class");

    JComboBox<String> cls = new JComboBox<>(priceMap.keySet().toArray(new String[0]));

    JLabel info = new JLabel("Seats & Price");
    info.setForeground(new Color(0,120,0));

    cls.addActionListener(e -> {
        String c = cls.getSelectedItem().toString();
        info.setText("Seats: " + seatAvailability.get(c) +
                     " | ₹" + priceMap.get(c));
    });

    // ================= SEAT =================
    JLabel prefLabel = new JLabel("Seat Preference");

    JComboBox<String> preference = new JComboBox<>(
            new String[]{"Lower","Middle","Upper","Side Lower","Side Upper"}
    );

    JButton seatBtn = new JButton("Select Seat");
    seatBtn.setBackground(new Color(0,102,204));
    seatBtn.setForeground(Color.WHITE);
    addButtonEffect(seatBtn);

    seatBtn.addActionListener(e -> {
        seatMapUI(cls.getSelectedItem().toString());
    });

    // ================= MEAL =================
    JLabel mealLabel = new JLabel("Meal");

    JComboBox<String> meal = new JComboBox<>(
            new String[]{"None","Veg (+300)","Non-Veg (+700)"}
    );

    // ================= PAYMENT =================
    JButton pay = new JButton("Proceed");
    pay.setBackground(new Color(0,150,0));
    pay.setForeground(Color.WHITE);
    addButtonEffect(pay);

    pay.addActionListener(e -> {

        StringBuilder allNames = new StringBuilder();

        for(int i=0; i<names.size(); i++){
            allNames.append(names.get(i).getText())
                    .append(" (Age:")
                    .append(ages.get(i).getText())
                    .append("), ");
        }

        String selectedClass = cls.getSelectedItem().toString();
        int basePrice = priceMap.get(selectedClass) * names.size();

        int mealCost = 0;
        String mealType = meal.getSelectedItem().toString();

        if(mealType.contains("Veg")) mealCost = 300 * names.size();
        if(mealType.contains("Non-Veg")) mealCost = 700 * names.size();

        int total = basePrice + mealCost;

        paymentPage(allNames.toString(), selectedClass, total);
    });

    // ================= ADD UI =================
    glass.add(title);
    glass.add(Box.createRigidArea(new Dimension(0,15)));

    glass.add(passengerPanel);
    glass.add(addBtn);

    glass.add(Box.createRigidArea(new Dimension(0,15)));
    glass.add(classLabel);
    glass.add(cls);
    glass.add(info);

    glass.add(Box.createRigidArea(new Dimension(0,10)));
    glass.add(prefLabel);
    glass.add(preference);

    glass.add(Box.createRigidArea(new Dimension(0,10)));
    glass.add(mealLabel);
    glass.add(meal);

    glass.add(Box.createRigidArea(new Dimension(0,10)));
    glass.add(seatBtn);

    glass.add(Box.createRigidArea(new Dimension(0,20)));
    glass.add(pay);

    bg.add(glass);
    book.add(bg);

    book.setLocationRelativeTo(null);
    book.setVisible(true);
}
static void addButtonEffect(JButton btn) {

    Color normal = btn.getBackground();
    Color hover = normal.darker();
    Color click = hover.darker();

    btn.setFocusPainted(false);
    btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

    btn.addMouseListener(new MouseAdapter() {

        public void mouseEntered(MouseEvent e) {
            btn.setBackground(hover);
        }

        public void mouseExited(MouseEvent e) {
            btn.setBackground(normal);
        }

        public void mousePressed(MouseEvent e) {
            btn.setBackground(click);
        }

        public void mouseReleased(MouseEvent e) {
            btn.setBackground(hover);
        }
    });
}
static void paymentPage(String name, String cls, int baseAmount) {

    JFrame payFrame = new JFrame("Payment");
    payFrame.setSize(450, 600);
    payFrame.setLocationRelativeTo(null);

    int gst = (int) (baseAmount * 0.0065);
    int total = baseAmount + gst;

    // 🌈 Gradient Background Panel
    JPanel bg = new JPanel() {
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            GradientPaint gp = new GradientPaint(0, 0, new Color(0, 102, 204),
                    0, getHeight(), new Color(0, 204, 255));
            g2.setPaint(gp);
            g2.fillRect(0, 0, getWidth(), getHeight());
        }
    };
    bg.setLayout(new GridBagLayout());

    // 💳 Card Panel
    JPanel card = new JPanel();
    card.setPreferredSize(new Dimension(350, 450));
    card.setBackground(Color.WHITE);
    card.setLayout(null);
    card.setBorder(BorderFactory.createLineBorder(new Color(200,200,200),1,true));

    // 🧾 Title
    JLabel title = new JLabel("Payment Details");
    title.setFont(new Font("Segoe UI", Font.BOLD, 20));
    title.setBounds(90, 20, 200, 30);

    // 💰 Fare Details
    JLabel base = new JLabel("Base Fare: ₹" + baseAmount);
    base.setBounds(40, 70, 200, 25);

    JLabel gstLabel = new JLabel("GST Charges: ₹" + gst);
    gstLabel.setBounds(40, 100, 200, 25);

    JLabel totalLabel = new JLabel("Total: ₹" + total);
    totalLabel.setBounds(40, 130, 250, 30);
    totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
    totalLabel.setForeground(new Color(0,153,0));

    // 💳 Payment Method
    JLabel methodLabel = new JLabel("Payment Method");
    methodLabel.setBounds(40, 180, 200, 25);

    String[] methods = {"UPI", "Card", "Net Banking"};
JComboBox<String> methodBox = new JComboBox<>(methods);
methodBox.setBounds(40, 210, 250, 30);

// 📦 Dynamic Panel (changes UI)
JPanel dynamicPanel = new JPanel();
dynamicPanel.setLayout(null);
dynamicPanel.setBounds(40, 250, 250, 120);
dynamicPanel.setBackground(Color.WHITE);

// 🔹 UPI Field
JTextField upiField = new JTextField();
upiField.setBounds(0, 0, 250, 30);

// 🧾 QR Code (use any image file in your project)
ImageIcon qrIcon = new ImageIcon("qr.png"); // 👉 put qr.png in project folder
JLabel qrLabel = new JLabel(qrIcon);
qrLabel.setBounds(60, 35, 120, 80);

// 💳 Bank List
String[] banks = {
        "State Bank of India",
        "HDFC Bank",
        "ICICI Bank",
        "Axis Bank",
        "Punjab National Bank",
        "Bank of Baroda"
};
JComboBox<String> bankBox = new JComboBox<>(banks);
bankBox.setBounds(0, 0, 250, 30);

// 💳 Card Field
JTextField cardField = new JTextField();
cardField.setBounds(0, 0, 250, 30);

// 🔄 Method Change Logic
methodBox.addActionListener(e -> {
    dynamicPanel.removeAll();

    String selected = (String) methodBox.getSelectedItem();

    if (selected.equals("UPI")) {
        dynamicPanel.add(upiField);
        dynamicPanel.add(qrLabel);
    } 
    else if (selected.equals("Net Banking")) {
        dynamicPanel.add(bankBox);
    } 
    else {
        dynamicPanel.add(cardField);
    }

    dynamicPanel.revalidate();
    dynamicPanel.repaint();
});

// 👉 Default load
dynamicPanel.add(upiField);
dynamicPanel.add(qrLabel);
    
    

    // 🔵 Rounded Button
    JButton payBtn = new JButton("Pay Now") {
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(0, 153, 0));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
            super.paintComponent(g);
        }
    };
    payBtn.setForeground(Color.WHITE);
    payBtn.setContentAreaFilled(false);
    payBtn.setFocusPainted(false);
    payBtn.setBounds(100, 360, 140, 40);
    payBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

    // 🎉 Success Action
    payBtn.addActionListener(e -> {

    JOptionPane.showMessageDialog(payFrame,
            "Payment Successful 🎉",
            "Success",
            JOptionPane.INFORMATION_MESSAGE);

    payFrame.dispose();

    // 🎟️ OPEN E-TICKET PAGE
    showETicket(name, cls, total);

});


    // ➕ Add components
    card.add(title);
    card.add(base);
    card.add(gstLabel);
    card.add(totalLabel);
    card.add(methodLabel);
    card.add(methodBox);
    card.add(dynamicPanel);
    card.add(payBtn);

    bg.add(card);
    payFrame.add(bg);
    payFrame.setVisible(true);
}
static void showETicket(String name, String cls, int totalAmount) {

    JFrame ticketFrame = new JFrame("E-Ticket");
    ticketFrame.setSize(500, 600);
    ticketFrame.setLocationRelativeTo(null);

    // 🌈 Gradient Background
    JPanel bg = new JPanel() {
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            GradientPaint gp = new GradientPaint(0, 0, new Color(20, 30, 48),
                    0, getHeight(), new Color(36, 59, 85));
            g2.setPaint(gp);
            g2.fillRect(0, 0, getWidth(), getHeight());
        }
    };
    bg.setLayout(new GridBagLayout());

    // 🎟️ Ticket Card
    JPanel card = new JPanel();
    card.setPreferredSize(new Dimension(380, 480));
    card.setBackground(Color.WHITE);
    card.setLayout(null);
    card.setBorder(BorderFactory.createLineBorder(Color.GRAY,1,true));

    // 🧾 Title
    JLabel title = new JLabel("IRCTC E-Ticket");
    title.setFont(new Font("Segoe UI", Font.BOLD, 20));
    title.setBounds(110, 20, 200, 30);

    // 🔢 Generate PNR
    String pnr = "PNR" + (int)(Math.random()*900000 + 100000);

    // 📄 Details
    JLabel pnrLabel = new JLabel("PNR: " + pnr);
    pnrLabel.setBounds(40, 80, 300, 25);

    JLabel nameLabel = new JLabel("Name: " + name);
    nameLabel.setBounds(40, 110, 300, 25);

    JLabel classLabel = new JLabel("Class: " + cls);
    classLabel.setBounds(40, 140, 300, 25);

    JLabel statusLabel = new JLabel("Status: CONFIRMED");
    statusLabel.setForeground(new Color(0,153,0));
    statusLabel.setBounds(40, 170, 300, 25);

    JLabel dateLabel = new JLabel("Date: " + new Date());
    dateLabel.setBounds(40, 200, 300, 25);

    JLabel fareLabel = new JLabel("Fare Paid: ₹" + totalAmount);
    fareLabel.setBounds(40, 230, 300, 25);

    // 🧾 QR (optional image)
    ImageIcon qrIcon = new ImageIcon("qr.png");
    JLabel qr = new JLabel(qrIcon);
    qr.setBounds(120, 270, 120, 100);

    // 🔘 Done Button
    JButton doneBtn = new JButton("Done");
    doneBtn.setBounds(130, 400, 120, 35);
    doneBtn.setBackground(new Color(0,153,0));
    doneBtn.setForeground(Color.WHITE);
    doneBtn.setFocusPainted(false);

    doneBtn.addActionListener(e -> ticketFrame.dispose());

    // ➕ Add components
    card.add(title);
    card.add(pnrLabel);
    card.add(nameLabel);
    card.add(classLabel);
    card.add(statusLabel);
    card.add(dateLabel);
    card.add(fareLabel);
    card.add(qr);
    card.add(doneBtn);

    bg.add(card);
    ticketFrame.add(bg);
    ticketFrame.setVisible(true);
}
// close class here
}
