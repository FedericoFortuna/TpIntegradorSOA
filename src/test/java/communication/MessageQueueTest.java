package communication;

import com.unlam.tp_integrador.communication.Message;
import com.unlam.tp_integrador.communication.MessageQueue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.concurrent.ConcurrentLinkedQueue;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MessageQueueTest {


    @InjectMocks
    private MessageQueue messageQueue;

    @Mock
    private Message mockedMessage;

    @Before
    public void setUp() {
        messageQueue = MessageQueue.getInstance();
    }

    @Test
    public void sendMessage_QueueContainsMessage() {
        messageQueue.sendMessage(mockedMessage);
        assertTrue(messageQueue.hasMessages());
    }

    @Test
    public void receiveMessage_QueueEmpty_ReturnsNull() {
        assertNull(messageQueue.receiveMessage());
    }

    @Test
    public void receiveMessage_QueueContainsMessage(){
        messageQueue.sendMessage(mockedMessage);
        assertEquals(mockedMessage, messageQueue.receiveMessage());
    }

    @Test
    public void hasMessages_QueueEmpty_ReturnsFalse() {
        assertFalse(messageQueue.hasMessages());
    }

}
