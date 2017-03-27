import websocket
import time
try:
    import thread
except ImportError:
    import _thread as thread

# Receive message from server
def on_message(ws, message):
    print("We should be doing opencv stuff here... %s" % message)

# Receive error from server
def on_error(ws, error):

   print(error)

# Close the socket
def on_close(ws):
    print("### closed ###")

def on_open(ws):
    print("open")
    def run(*args):
        # Default position should be "don't move"
        angles = "0,0"
        while(True):
            # Move if Java GUI changed the file
            file = open("data.txt", "r")
            angles = file.readline()
            if (angles != ""):
                print("sending %s" % angles)
                ws.send("%s" % angles)
            file.close()
            # Make sure we don't keep moving if key isn't pressed
            # by erasing the file
            file = open("data.txt", "w")
            file.close()
            # Wait before checking again, so Java has time to edit
            time.sleep(0.1)
        # If we somehow leave the loop, close safely
        ws.close()
        print("terminating thread")
    thread.start_new_thread(run, ())

if __name__ == "__main__":
    websocket.enableTrace(True)
    ws = websocket.WebSocketApp("ws://172.22.11.2:6123",
                                on_message = on_message,
                                on_error = on_error,
                                on_close = on_close)
    ws.on_open = on_open
    ws.run_forever()
