/*
ID: acatesby
LANG: JAVA
PROB: billboard
*/
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class billboard {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("billboard.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("billboard.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		
		int x1 = Integer.parseInt(st.nextToken());
		int y1 = Integer.parseInt(st.nextToken());
		int x2 = Integer.parseInt(st.nextToken());
		int y2 = Integer.parseInt(st.nextToken());
		
		Rectangle lawnmower = new Rectangle(x1,y1,x2-x1,y2-y1);
		st = new StringTokenizer(f.readLine());
		int x3 = Integer.parseInt(st.nextToken());
		int y3 = Integer.parseInt(st.nextToken());
		int x4 = Integer.parseInt(st.nextToken());
		int y4 = Integer.parseInt(st.nextToken());
		
		Rectangle grass = new Rectangle(x1,y1,x2-x1,y2-y1);
		
		if(lawnmower.contains(grass.x,grass.y)) {
			out.print(lawnmower.width*lawnmower.height);
			out.close();
			return;
		}if(grass.contains(lawnmower.x,lawnmower.y)) {
			out.print(0);
			out.close();
			return;
		}
		
		Rectangle inter = (Rectangle) grass.createIntersection(lawnmower);
		
		if(inter.height == 0 || inter.width == 0) {
			out.print(lawnmower.height*lawnmower.width);
			out.close();
			return;
		}
			
		int rx;
		int ry;
		
		if(inter.width >= lawnmower.width) {
			ry = Math.abs(lawnmower.y - inter.y + lawnmower.height + inter.height);
		} else {
			ry = lawnmower.y + lawnmower.height;
		}
		
		if(inter.height >= lawnmower.height) {
			rx = Math.abs(lawnmower.x - inter.x + lawnmower.width + inter.width);
		} else {
			rx = lawnmower.x + lawnmower.width;
		}
		
		out.print(rx*ry);
		out.close();
		
		
		
	}
}
