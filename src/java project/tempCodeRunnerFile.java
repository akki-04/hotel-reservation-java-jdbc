catch(InterruptedException e, SQLException d){
            System.out.println("Error in thread bcz thread sleep");
            throw new RuntimeException(e);
        }